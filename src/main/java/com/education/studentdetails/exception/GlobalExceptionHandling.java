package com.education.studentdetails.exception;

import com.education.studentdetails.model.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorResponse>> handleMethodArgumentsNotValidException (MethodArgumentNotValidException e){
        List<ValidationErrorResponse> validationErrorResponseList = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(fieldError.getField(),fieldError.getDefaultMessage());
            validationErrorResponseList.add(validationErrorResponse);
        });
        return ResponseEntity.ok(validationErrorResponseList);
    }
}


