package jpa.innorunproject.schedule.exception;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ScheduleServiceException{

    public ScheduleNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
