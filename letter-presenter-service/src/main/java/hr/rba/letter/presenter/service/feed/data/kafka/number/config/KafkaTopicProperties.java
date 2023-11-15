package hr.rba.letter.presenter.service.feed.data.kafka.number.config;

import org.springframework.boot.context.properties.bind.DefaultValue;

public record KafkaTopicProperties(String name, String keySerializer, String valueSerializer, String acks,
                                   String keyDeserializer, String valueDeserializer, String consumerGroupId,
                                   @DefaultValue("EARLIEST") AutoOffsetReset autoOffsetReset) {
    public enum AutoOffsetReset {
        NONE, EARLIEST, LATEST
    }
}
