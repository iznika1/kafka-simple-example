package hr.rba.random.number.generator.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "random-number-generator")
public class RandomNumberGeneratorProperties {

    @NestedConfigurationProperty
    private final NumberRangeProperties range;
    private final Duration period;

    public RandomNumberGeneratorProperties(@NonNull NumberRangeProperties range,
                                           @NonNull @DefaultValue("5s") Duration period) {
        this.range = range;
        this.period = period;
    }

    public record NumberRangeProperties(@NonNull @DefaultValue("0") Long from,
                                        @NonNull @DefaultValue("1000") Long to) {
    }
}
