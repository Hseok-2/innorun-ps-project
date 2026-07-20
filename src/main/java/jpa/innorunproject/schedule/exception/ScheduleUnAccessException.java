package jpa.innorunproject.schedule.exception;

import org.springframework.http.HttpStatus;

public class ScheduleUnAccessException extends ScheduleServiceException {

    public ScheduleUnAccessException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
