package jpa.innorunproject.user.exception;

import org.springframework.http.HttpStatus;

public class UserInvalidPasswordException extends UserServiceException {

    public UserInvalidPasswordException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
