package cz.zelo.byts.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends BytsException {

    public UnauthorizedException() {
        super(BytsError.UNAUTHORIZED_ACCESS, "Unauthorized access");
    }
}
