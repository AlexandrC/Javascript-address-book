package com.etnetera.hr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {JSDuplicate.class})
    public ResponseEntity<ErrorMessage> handleJSDuplicateException(RuntimeException ex){
        ErrorMessage errorMessage = new ErrorMessage("No connection to webserver", ex.getMessage(), 409);
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

}
