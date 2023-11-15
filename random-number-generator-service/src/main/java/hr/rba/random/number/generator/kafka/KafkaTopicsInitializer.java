package hr.rba.random.number.generator.kafka;

import hr.rba.random.number.generator.kafka.KafkaTopicInitializationException;
import hr.rba.random.number.generator.kafka.config.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.core.NestedExceptionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.apache.kafka.common.config.TopicConfig.CLEANUP_POLICY_COMPACT;
import static org.apache.kafka.common.config.TopicConfig.CLEANUP_POLICY_DELETE;

@Slf4j
@RequiredArgsConstructor
public class KafkaTopicsInitializer {

    private final AdminClient adminClient;

    public synchronized void createKafkaTopic(KafkaTopicProperties topicProperties) {
        String topicName = topicProperties.name();
        KafkaTopicProperties.CreateTopicIfMissing createTopicIfMissing = topicProperties.createTopicIfMissing();

        NewTopic newTopic = new NewTopic(
                topicName,
                createTopicIfMissing.createWithNumOfPartitions(),
                createTopicIfMissing.replicationFactor().shortValue()
        ).configs(getTopicConfigs(createTopicIfMissing));

        try {
            log.info("Creating topic: {}", newTopic);
            adminClient.createTopics(List.of(newTopic)).all().get();
        } catch (InterruptedException | ExecutionException e) {
            if (NestedExceptionUtils.getRootCause(e) instanceof TopicExistsException) {
                log.info("Topic '{}' is not being created because it already exists.", topicName);
            } else {
                log.error("Error while creating topics: {}", topicProperties);
                throw new KafkaTopicInitializationException(e);
            }
        }
    }

    private Map<String, String> getTopicConfigs(KafkaTopicProperties.CreateTopicIfMissing createTopicIfMissing) {
        Map<String, String> configs = new HashMap<>();

        configs.put(TopicConfig.RETENTION_MS_CONFIG, createTopicIfMissing.retentionMs().toString());
        configs.put(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, createTopicIfMissing.minInsyncReplicas().toString());
        configs.put(
                TopicConfig.CLEANUP_POLICY_CONFIG,
                createTopicIfMissing.compacted() ? CLEANUP_POLICY_COMPACT : CLEANUP_POLICY_DELETE
        );

        return configs;
    }
}
