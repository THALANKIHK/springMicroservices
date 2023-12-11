package com.harihkstands.usermanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDeatils> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDeatils errorDeatils =new ErrorDeatils(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
            return new ResponseEntity<>(errorDeatils, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDeatils>  handleEmailAlreadyExistsException(EmailAlreadyExistsException exception,
                                                                    WebRequest webRequest){
        ErrorDeatils errorDeatils =new ErrorDeatils(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorDeatils, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDeatils> handleGlobalException(Exception exception,
                                                              WebRequest webRequest){
        ErrorDeatils errorDeatils =new ErrorDeatils(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDeatils, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
