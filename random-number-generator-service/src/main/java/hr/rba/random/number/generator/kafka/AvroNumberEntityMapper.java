package hr.rba.random.number.generator.kafka;

import hr.rba.avro.NumberEntity;
import lombok.NonNull;

import java.time.Clock;
import java.time.Instant;

public final class AvroNumberEntityMapper {

    private AvroNumberEntityMapper() {}

    public static NumberEntity mapToAvro(@NonNull Long number, @NonNull String trackingId) {
        return new NumberEntity(
                Instant.now(Clock.systemUTC()),
                trackingId,
                number
        );
    }

}
