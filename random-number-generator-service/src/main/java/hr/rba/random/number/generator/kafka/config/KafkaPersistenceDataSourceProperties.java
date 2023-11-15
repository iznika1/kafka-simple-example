package hr.rba.random.number.generator.kafka.config;

import hr.rba.random.number.generator.kafka.KafkaPersistenceDataSourceCondition;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Conditional;

@Conditional(KafkaPersistenceDataSourceCondition.class)
@ConfigurationProperties(prefix = "random-number-generator.persistence-datasource")
public record KafkaPersistenceDataSourceProperties(@NestedConfigurationProperty @NonNull KafkaProperties kafka,
                                                   @NestedConfigurationProperty @NonNull KafkaTopicProperties kafkaTopic){}
