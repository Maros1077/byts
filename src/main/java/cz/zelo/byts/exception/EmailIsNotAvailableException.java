package cz.zelo.byts.exception;

import lombok.Getter;

@Getter
public class EmailIsNotAvailableException extends BytsException {

    public EmailIsNotAvailableException(String email) {
        super(BytsError.EMAIL_IS_NOT_AVAILABLE, "Email " + email + " is not available");
    }

}
