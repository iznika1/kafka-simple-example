package hr.rba.letter.presenter.service.feed.data.kafka.number.config;

import hr.rba.avro.NumberEntity;
import hr.rba.letter.presenter.service.feed.data.kafka.number.util.KafkaConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NumberEntity>> numberEntityKafkaListenerContainerFactory(
            KafkaFeedNumberProperties kafkaFeedNumberProperties) {
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, NumberEntity>();
        containerFactory.setConsumerFactory(createConsumerFactory(kafkaFeedNumberProperties.getKafka(), kafkaFeedNumberProperties));
        containerFactory.getContainerProperties().setPollTimeout(1000);
        containerFactory.setBatchListener(true);
        containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return containerFactory;
    }

    private DefaultKafkaConsumerFactory<Object, Object> createConsumerFactory(KafkaProperties kafkaProperties,
                                                                              KafkaFeedNumberProperties kafkaFeedNumberProperties) {

        var props = KafkaConfigUtils.createKafkaConsumerProperties(kafkaProperties, kafkaFeedNumberProperties.getKafkaTopic());
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
