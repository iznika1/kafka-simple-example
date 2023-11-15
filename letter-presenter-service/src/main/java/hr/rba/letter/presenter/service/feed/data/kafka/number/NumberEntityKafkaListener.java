package hr.rba.letter.presenter.service.feed.data.kafka.number;

import hr.rba.avro.NumberEntity;
import hr.rba.letter.presenter.service.NumberEntityService;
import hr.rba.letter.presenter.service.feed.data.kafka.number.config.KafkaFeedNumberProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class NumberEntityKafkaListener {

    private final KafkaFeedNumberProperties kafkaFeedNumberProperties;
    private final NumberEntityService numberEntityService;

    @KafkaListener(
            topics = "#{numberEntityKafkaListener.getTopicName()}",
            containerFactory = "numberEntityKafkaListenerContainerFactory"
    )
    public void consumeKafkaRecords(List<ConsumerRecord<String, NumberEntity>> records, Acknowledgment acknowledgment) {
        log.debug("Reading #{} number entities from kafka", records.size());
        log.trace("Number entity records from kafka: '{}'", records.stream().map(ConsumerRecord::toString).collect(joining("||||")));

        List<NumberEntityDto> numberEntityDtoList = records.stream()
                .map(ConsumerRecord::value)
                .map(numberEntity -> new NumberEntityDto(
                        numberEntity.getNumber(),
                        numberEntity.getTrackingId()
                ))
                .toList();

        numberEntityService.processAndSave(numberEntityDtoList);

        acknowledgment.acknowledge();
        log.debug("Acknowledged: #{}", records.size());
    }

    public String getTopicName() {
        return kafkaFeedNumberProperties.getKafkaTopic().name();
    }
}
