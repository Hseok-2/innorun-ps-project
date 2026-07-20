package jpa.innorunproject.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserServiceException extends RuntimeException {

    private final HttpStatus status;

    public UserServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
