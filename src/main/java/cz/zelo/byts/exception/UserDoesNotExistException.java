package cz.zelo.byts.exception;

import lombok.Getter;

@Getter
public class UserDoesNotExistException extends BytsException {

    public UserDoesNotExistException() {
        super(BytsError.UNAUTHORIZED_ACCESS, "Unauthorized access");
    }
}
