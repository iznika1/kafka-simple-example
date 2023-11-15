package hr.rba.random.number.generator.kafka;

import hr.rba.random.number.generator.core.persistence.PersistenceType;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class KafkaPersistenceDataSourceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        PersistenceType persistenceType = conditionContext.getEnvironment()
                .getProperty("random-number-generator.persistence.type",
                        PersistenceType.class, PersistenceType.KAFKA);
        return persistenceType == PersistenceType.KAFKA;
    }
}
