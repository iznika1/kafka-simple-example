package hr.rba.random.number.generator.core.process;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class RandomNumberGenerator {

    Long generateNumber(@NonNull Long from, @NonNull Long to) {
        log.debug("Generate random number between {} and {}" , from, to);
        var randomNumber = new Random().nextLong(from, to);
        log.debug("Generated random number: {}" , randomNumber);

        return randomNumber;
    }

}
