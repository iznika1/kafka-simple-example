package hr.rba.letter.presenter.service.feed.data.kafka.number.util;

import hr.rba.letter.presenter.service.feed.data.kafka.number.config.KafkaProperties;
import hr.rba.letter.presenter.service.feed.data.kafka.number.config.KafkaTopicProperties;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfigUtils {

    public static final String SCHEMA_REGISTRY_PROPERTY = "schema.registry.url";

    public static Map<String, Object> createKafkaConsumerProperties(KafkaProperties kafkaProperties,
                                                                    KafkaTopicProperties topicProperties) {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers());
        props.put(SCHEMA_REGISTRY_PROPERTY, kafkaProperties.schemaRegistry().url());

        props.put(ConsumerConfig.GROUP_ID_CONFIG, topicProperties.consumerGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, topicProperties.keyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, topicProperties.valueDeserializer());

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, topicProperties.autoOffsetReset().name().toLowerCase());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        return props;
    }
}
