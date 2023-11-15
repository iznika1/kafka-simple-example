package hr.rba.random.number.generator.kafka.config;

import hr.rba.random.number.generator.kafka.KafkaPersistenceDataSourceCondition;
import hr.rba.random.number.generator.kafka.KafkaTopicsInitializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@Conditional(KafkaPersistenceDataSourceCondition.class)
public class KafkaConfig {

    private final KafkaPersistenceDataSourceProperties properties;

    public KafkaConfig(KafkaPersistenceDataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean
    AdminClient adminClient() {
        Map<String, Object> adminClientProperties = new HashMap<>();
        adminClientProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                properties.kafka().bootstrapServers());
        adminClientProperties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 5000);

        return AdminClient.create(adminClientProperties);
    }

    @Bean
    KafkaTopicsInitializer kafkaTopicsInitializer(AdminClient adminClient) {
        var kafkaTopicsInitializer = new KafkaTopicsInitializer(adminClient);
        kafkaTopicsInitializer.createKafkaTopic(properties.kafkaTopic());
        return kafkaTopicsInitializer;
    }
}
