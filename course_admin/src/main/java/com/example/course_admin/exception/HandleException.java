package com.example.course_admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Date;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException e){
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage(), LocalDate.now().toString());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(BadRequestException e){
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDate.now().toString());
    }

    //xu ly e con lai 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleOtherException(Exception e){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), LocalDate.now().toString());
    }
}
