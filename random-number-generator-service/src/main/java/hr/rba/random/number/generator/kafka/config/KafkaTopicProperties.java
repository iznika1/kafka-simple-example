package hr.rba.random.number.generator.kafka.config;

import lombok.NonNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record KafkaTopicProperties(String name, String keySerializer, String valueSerializer, String acks,
                                   String keyDeserializer, String valueDeserializer, String consumerGroupId,
                                   KafkaTopicProperties.AutoOffsetReset autoOffsetReset,
                                   KafkaTopicProperties.CreateTopicIfMissing createTopicIfMissing) {
    public enum AutoOffsetReset {
        NONE, EARLIEST, LATEST
    }

    public record CreateTopicIfMissing(@NonNull @DefaultValue("1") Integer createWithNumOfPartitions,
                                       @NonNull @DefaultValue("1") Integer replicationFactor,
                                       @NonNull @DefaultValue("1") Integer minInsyncReplicas,
                                       Long retentionMs,
                                       boolean compacted) {

        @Override
        public Long retentionMs() {
            return retentionMs == null ? 2629800000L : retentionMs;
        }
    }
}
