package hr.rba.random.number.generator.core.persistence;

import lombok.NonNull;

public interface PersistenceService {

    void persist(@NonNull Long number);
}
