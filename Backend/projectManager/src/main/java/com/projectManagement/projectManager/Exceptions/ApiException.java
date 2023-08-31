package com.projectManagement.projectManager.Exceptions;


public class ApiException extends RuntimeException {
    public ApiException(String message)
    {
        super(message);
    }

    public ApiException()
    {
        super();
    }
}
