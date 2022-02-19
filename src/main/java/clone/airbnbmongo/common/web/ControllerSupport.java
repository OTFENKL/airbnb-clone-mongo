package clone.airbnbmongo.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerSupport {

    public static <T> ResponseEntity<?> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static <T> ResponseEntity<?> create(T body) {
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<?> badRequest(T body) {
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<?> notFound(T body) {
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<?> internalError(T body) {
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
