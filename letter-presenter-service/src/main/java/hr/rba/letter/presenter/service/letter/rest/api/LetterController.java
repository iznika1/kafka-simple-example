package hr.rba.letter.presenter.service.letter.rest.api;

import hr.rba.letter.presenter.service.letter.service.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LetterController implements LetterApi {

    private final LetterService letterService;

    @Override
    public ResponseEntity<LetterCountResponse> fetchLetterCount(String letter) {
        var letterCountDto = letterService.countByLetter(letter);
        // todo: create mapper
        var letterCountResponse = new LetterCountResponse(
                letterCountDto.letter(),
                letterCountDto.count()
        );

        return ResponseEntity.ok(
                letterCountResponse
        );
    }
}
