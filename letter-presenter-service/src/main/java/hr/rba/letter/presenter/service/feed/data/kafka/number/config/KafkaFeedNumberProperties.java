package hr.rba.letter.presenter.service.feed.data.kafka.number.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "letter-presenter.kafka-feed-number-datasource")
public class KafkaFeedNumberProperties {

    @NestedConfigurationProperty
    private final KafkaProperties kafka;
    @NestedConfigurationProperty
    private final KafkaTopicProperties kafkaTopic;

}
