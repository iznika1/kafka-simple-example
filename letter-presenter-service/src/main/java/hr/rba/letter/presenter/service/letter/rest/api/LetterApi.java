package hr.rba.letter.presenter.service.letter.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/letter")
@Tag(name = "Letter", description = "Letter related operations")
public interface LetterApi {

    @Operation(summary = "Returns a count of stored letter")
    @GetMapping(path = "/{letter}/count", consumes = "application/json", produces = "application/json")
    ResponseEntity<LetterCountResponse> fetchLetterCount(
            @Parameter(required = true, description = "Represents a letter for which a count can be obtained. Current possible values [R, B, RBA]",
                    example = "R")
            @PathVariable("letter")@NotNull @Size(max = 3) String letter);
}
