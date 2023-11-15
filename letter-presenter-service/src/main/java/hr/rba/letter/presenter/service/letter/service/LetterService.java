package hr.rba.letter.presenter.service.letter.service;

import hr.rba.letter.presenter.service.letter.repository.Letter;
import hr.rba.letter.presenter.service.letter.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public LetterCountDto countByLetter(String letter) {
        var letterCount = letterRepository.countByLetter(letter);

        return new LetterCountDto(letter, letterCount);
    }

    public void saveAll(List<LetterDto> letterDtoList) {
        //todo: mapper
        var letterList = letterDtoList.stream()
                .map(letterDto -> new Letter(
                        letterDto.letter(),
                        letterDto.trackingId()
                ))
                .toList();

        letterRepository.saveAll(letterList);
    }
}
