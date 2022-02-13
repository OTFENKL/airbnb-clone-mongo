package clone.airbnbmongo.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorForm {

    private String code;
    private String cause;

    private ErrorForm(String code, String cause) {
        this.code = code;
        this.cause = cause;
    }

    public static ErrorForm of(ErrorMessageConstants message) {
        return new ErrorForm(message.getCode(), message.getDescription());
    }
}
