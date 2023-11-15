package hr.rba.random.number.generator.kafka.util;

import hr.rba.random.number.generator.kafka.config.KafkaProperties;
import hr.rba.random.number.generator.kafka.config.KafkaTopicProperties;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfigUtils {

    public static final String SCHEMA_REGISTRY_PROPERTY = "schema.registry.url";

    public static Map<String, Object> createKafkaProducerProperties(KafkaProperties kafkaProperties,
                                                                    KafkaTopicProperties topicProperties) {

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServers());
        props.put(SCHEMA_REGISTRY_PROPERTY, kafkaProperties.schemaRegistry().url());

        props.put(ProducerConfig.ACKS_CONFIG, topicProperties.acks());

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, topicProperties.keySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, topicProperties.valueSerializer());

        return props;
    }
}
