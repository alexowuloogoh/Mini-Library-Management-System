package com.project4.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> badRequestHandler(MethodArgumentNotValidException exception){

        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        fieldErrors.forEach(e ->
                errorMap.put(e.getField(), e.getDefaultMessage()));

        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String, String> internalServerErrorHandler(SQLIntegrityConstraintViolationException exception){

        Map<String, String> errorMap = new HashMap<>();
        exception.forEach(error ->
                errorMap.put(error.getLocalizedMessage(), error.getMessage()));

        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException (UserNotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookNotFoundException.class)
    public String bookNotFoundException (BookNotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BorrowedBookNotFoundException.class)
    public String borrowedBookNotFoundException (BorrowedBookNotFoundException exception){
        return exception.getMessage();
    }
}
