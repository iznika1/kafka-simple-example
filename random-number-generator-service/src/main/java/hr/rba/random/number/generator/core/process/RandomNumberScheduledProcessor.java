package hr.rba.random.number.generator.core.process;

import hr.rba.random.number.generator.config.RandomNumberGeneratorProperties;
import hr.rba.random.number.generator.core.persistence.PersistenceService;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class RandomNumberScheduledProcessor {

    private final static String TASK_NAME = UUID.randomUUID().toString();

    private final RandomNumberGeneratorProperties randomNumberGeneratorProperties;
    private final RandomNumberGenerator randomNumberGenerator;
    private final hr.rba.random.number.generator.core.persistence.PersistenceService persistenceService;
    private final ThreadPoolTaskScheduler taskScheduler;

    public RandomNumberScheduledProcessor(RandomNumberGeneratorProperties randomNumberGeneratorProperties,
                                          RandomNumberGenerator randomNumberGenerator,
                                          PersistenceService persistenceService) {
        this.randomNumberGeneratorProperties = randomNumberGeneratorProperties;
        this.randomNumberGenerator = randomNumberGenerator;
        this.persistenceService = persistenceService;
        this.taskScheduler = initTaskScheduler();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("Initializing task scheduler={}", TASK_NAME);
        startProcessing();
        log.info("Initialized task scheduler={}", TASK_NAME);
    }

    @PreDestroy
    public void destroy() {
        log.info("PreDestroy received -> shutting down task scheduler={}", TASK_NAME);
        taskScheduler.shutdown();
        log.info("PreDestroy received -> completed shutdown task scheduler={}", TASK_NAME);
    }

    public void process() {
        var randomRange = randomNumberGeneratorProperties.getRange();
        var generatedNumber = randomNumberGenerator.generateNumber(randomRange.from(), randomRange.to());

        persistenceService.persist(generatedNumber);
    }

    private void startProcessing() {
        var schedulePeriod = randomNumberGeneratorProperties.getPeriod();
        log.info("Starting {} processing new random number with period {}", TASK_NAME, schedulePeriod);

        taskScheduler.scheduleAtFixedRate(this::process, schedulePeriod);
    }

    private ThreadPoolTaskScheduler initTaskScheduler() {
        ThreadPoolTaskScheduler res = new ThreadPoolTaskScheduler();
        res.setPoolSize(1); // todo resolve from configuration
        res.setThreadNamePrefix(TASK_NAME + '-');
        res.setAwaitTerminationSeconds(60); // todo resolve from configuration
        res.initialize();
        return res;
    }

}
