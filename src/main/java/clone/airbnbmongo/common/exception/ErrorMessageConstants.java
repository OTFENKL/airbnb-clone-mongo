package clone.airbnbmongo.common.exception;

import lombok.Getter;

@Getter
public enum ErrorMessageConstants {
    ACCOMMODATION_NOT_FOUND("A0001", "The accommodation could not be found.");

    private String code;
    private String description;

    ErrorMessageConstants(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
