package cz.zelo.byts.exception;

import cz.exodus.jsend.network.exception.BaseException;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class BytsException extends BaseException {

    final BytsError bytsError;

    public BytsException(BytsError bytsError, String message) {
        this(bytsError, message, null);
    }

    public BytsException(BytsError bytsError, String message, Throwable cause) {
        super(message, cause, UUID.randomUUID().toString());
        this.bytsError = bytsError;
    }

}
