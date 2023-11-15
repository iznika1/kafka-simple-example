package hr.rba.letter.presenter.service;

import hr.rba.letter.presenter.service.feed.data.kafka.number.NumberEntityDto;
import hr.rba.letter.presenter.service.letter.repository.Letter;
import hr.rba.letter.presenter.service.letter.repository.LetterRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NumberEntityService {

    private final LetterRepository letterRepository;

    public void processAndSave(@NotNull List<NumberEntityDto> numberEntityDtoList) {
        //todo add total entity for processing metrics
        numberEntityDtoList
                .forEach(this::processAndSave);
    }

    public void processAndSave(@NotNull NumberEntityDto numberEntity) {
        if (isDivisibleByThreeAndFive(numberEntity)) {
            String resolvedLetter = resolveLetter(numberEntity.number());

            letterRepository.save(new Letter(resolvedLetter, numberEntity.trackingId()));
            log.debug("Processed and saved: {}", numberEntity);
            //todo add successfully entity process metrics
        } else {
            //todo add skip entity process metrics, maybe add to dlt topic
            log.trace("Skipping message since number was not divisible by 3 or 5 (trackingId={})", numberEntity.trackingId());
        }
    }

    private boolean isDivisibleByThreeAndFive(@NotNull NumberEntityDto numberEntity) {
        var number = numberEntity.number();
        return ((number % 5 == 0) || (number % 3 == 0));
    }

    private String resolveLetter(long number) {
        if (number % 3 == 0 && number % 5 == 0)
            return "RBA";
        else if (number % 5 == 0) {
            return "B";
        }
        return "R";
    }

}
