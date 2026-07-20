package jpa.innorunproject.global.exception;

import jpa.innorunproject.schedule.exception.ScheduleServiceException;
import jpa.innorunproject.user.exception.UserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("입력값이 올바르지 않습니다");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // 스케줄 에러 핸들링
    @ExceptionHandler(ScheduleServiceException.class)
    public ResponseEntity<String> handlerSchedule(ScheduleServiceException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }

    // 유저 에러 핸들링
    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<String> handlerUser(UserServiceException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }
}
