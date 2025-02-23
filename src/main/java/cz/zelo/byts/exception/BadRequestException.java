package cz.zelo.byts.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends BytsException {

    public BadRequestException() {
        super(BytsError.REQUEST_INVALID, "Bad request");
    }

    public BadRequestException(String message) {
        super(BytsError.REQUEST_INVALID, "Bad request: " + message);
    }
}
