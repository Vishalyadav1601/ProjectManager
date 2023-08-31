package com.projectManagement.projectManager.Exceptions;

import com.projectManagement.projectManager.DTO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IdResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> idResourceNotFoundException(IdResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> ApiException (ApiException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> nameresourcenotfoundException(NameResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }
}
