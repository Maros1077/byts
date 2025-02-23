package cz.zelo.byts.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import cz.exodus.jsend.network.exception.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static cz.exodus.jsend.network.exception.ErrorType.ERROR;
import static cz.exodus.jsend.network.exception.ErrorType.FAIL;

@Getter
@AllArgsConstructor
public enum BytsError {

    REQUEST_INVALID(1000, FAIL, HttpStatus.BAD_REQUEST),
    EMAIL_IS_NOT_AVAILABLE(1001, FAIL, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ACCESS(1002, FAIL, HttpStatus.UNAUTHORIZED),

    AUTHENTICATION_FAILED(1001, FAIL, HttpStatus.UNAUTHORIZED),
    IDENTITY_NOT_FOUND(1002, FAIL, HttpStatus.BAD_REQUEST),
    IDENTITY_ALREADY_EXISTS(1003, FAIL, HttpStatus.BAD_REQUEST),
    TAG_TYPE_DOES_NOT_EXISTS(1004, FAIL, HttpStatus.BAD_REQUEST),
    APPLICATION_DOES_NOT_EXISTS(1005, FAIL, HttpStatus.BAD_REQUEST),
    AUTH_POINT_TYPE_DOES_NOT_EXISTS(1007, FAIL, HttpStatus.BAD_REQUEST),
    OPERATION_DOES_NOT_EXISTS(1008, FAIL, HttpStatus.BAD_REQUEST),
    DUPLICATION_IN_REQUEST_FOUND(1009, FAIL, HttpStatus.BAD_REQUEST),
    AUTH_POINT_NOT_SET(1010, FAIL, HttpStatus.BAD_REQUEST),
    AUTH_POINT_VALUE_NOT_SET(1011, FAIL, HttpStatus.BAD_REQUEST),
    OIDC_CLIENT_DOES_NOT_EXISTS(1012, FAIL, HttpStatus.BAD_REQUEST),
    UNEXPECTED(2000, ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    ;


    private final int code;
    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    @JsonValue
    public int getJsonValue() {
        return code;
    }

    @JsonCreator
    public static BytsError fromJsonValue(Integer jsonValue) {
        return Optional.ofNullable(jsonValue)
                .flatMap(BytsError::fromCode)
                .orElse(null);
    }

    public static Optional<BytsError> fromCode(int code) {
        for (BytsError e : values()) {
            if (e.getCode() == code) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

}

