package hr.rba.random.number.generator.kafka.config;

import lombok.NonNull;

import java.util.List;

public record KafkaProperties(@NonNull List<String> bootstrapServers,
                              @NonNull SchemaRegistry schemaRegistry) {

    public record SchemaRegistry(String url) {
        public SchemaRegistry(@NonNull String url) {
            this.url = url;
        }
    }
}

