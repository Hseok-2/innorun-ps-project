package jpa.innorunproject.user.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserServiceException {

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
