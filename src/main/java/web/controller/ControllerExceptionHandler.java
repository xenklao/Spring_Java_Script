package web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import web.Exception.BadRequestException;
import web.Exception.ErrorMessage;
import web.Exception.ResourceNotFoundException;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

        return message;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage userUsernameExistException (DataIntegrityViolationException ex, WebRequest request){
        return  new ErrorMessage(
            HttpStatus.CONFLICT.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(true)
        );
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException (BadRequestException ex, WebRequest request){
        return  new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(true)
        );
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
//        ErrorMessage message = new ErrorMessage(
//            HttpStatus.INTERNAL_SERVER_ERROR.value(),
//            new Date(),
//            ex.getMessage(),
//            request.getDescription(false));
//
//        return message;
//    }
}
