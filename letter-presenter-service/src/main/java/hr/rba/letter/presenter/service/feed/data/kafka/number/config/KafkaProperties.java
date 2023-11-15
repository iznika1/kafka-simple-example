package hr.rba.letter.presenter.service.feed.data.kafka.number.config;

import lombok.NonNull;

import java.util.List;

public record KafkaProperties(@NonNull List<String> bootstrapServers,
                              @NonNull SchemaRegistry schemaRegistry) {
    public record SchemaRegistry(@NonNull String url) {}
}

