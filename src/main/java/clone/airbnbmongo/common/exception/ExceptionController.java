package clone.airbnbmongo.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static clone.airbnbmongo.common.exception.ErrorForm.of;
import static clone.airbnbmongo.common.exception.ErrorMessageConstants.*;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {AccommodationNotFoundException.class})
    public ResponseEntity<?> accommodationNotFoundException() {
        return ResponseEntity.badRequest()
                .body(of(ACCOMMODATION_NOT_FOUND));
    }
}
