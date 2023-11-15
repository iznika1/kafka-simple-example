package hr.rba.random.number.generator.kafka;

import hr.rba.avro.NumberEntity;
import hr.rba.random.number.generator.core.persistence.PersistenceService;
import hr.rba.random.number.generator.kafka.config.KafkaPersistenceDataSourceProperties;
import hr.rba.random.number.generator.kafka.config.KafkaProperties;
import hr.rba.random.number.generator.kafka.config.KafkaTopicProperties;
import hr.rba.random.number.generator.kafka.util.KafkaConfigUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Conditional(KafkaPersistenceDataSourceCondition.class)
@EnableConfigurationProperties(value = KafkaPersistenceDataSourceProperties.class)
public class KafkaPersistenceService implements PersistenceService {

    private final KafkaProducer<String, NumberEntity> kafkaProducer;
    private final KafkaProperties kafkaProperties;
    private final KafkaTopicProperties kafkaTopicProperties;

    public KafkaPersistenceService(KafkaPersistenceDataSourceProperties kafkaPersistenceDataSourceProperties) {

        this.kafkaProperties = kafkaPersistenceDataSourceProperties.kafka();
        this.kafkaTopicProperties = kafkaPersistenceDataSourceProperties.kafkaTopic();
        this.kafkaProducer = new KafkaProducer<>(
                createKafkaProducerProperties(kafkaProperties, kafkaTopicProperties)
        );
    }

    @Override
    public void persist(@NonNull Long number) {

        var trackingId = UUID.randomUUID().toString();
        var numberEntityAvro = AvroNumberEntityMapper.mapToAvro(number, trackingId);
        // todo: publish to dlt topic or something like this if entity is not as expected

        var producerRecord =
                new ProducerRecord<>(kafkaTopicProperties.name(), trackingId, numberEntityAvro);

        try {
            // todo see if we need do async or sync, if we can async we can't catch exceptions like kafka is down, only we can catch schema validation
            kafkaProducer.send(producerRecord);
        } catch (Exception ex) {
            //todo: handle logic depends on exception
            log.error("An error occurred while trying to produce data to the Kafka cluster (topic=[{}], bootstrapServers=[{}])",
                    kafkaTopicProperties.name(), kafkaProperties.bootstrapServers(), ex
            );
            //todo add failure metrics
        }
    }

    private Map<String, Object> createKafkaProducerProperties(@NonNull KafkaProperties outputKafkaProperties,
                                                              @NonNull KafkaTopicProperties outputTopicProperties) {

        return KafkaConfigUtils.createKafkaProducerProperties(outputKafkaProperties, outputTopicProperties);
    }
}
