package com.khaled.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NoSuchEmpExistsException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchEmpExistsHandler(NoSuchEmpExistsException e){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmpAlreadyExistsException.class)
     public ResponseEntity<ErrorResponse> handleEmpAlreadyExistsException(EmpAlreadyExistsException e){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }







}
