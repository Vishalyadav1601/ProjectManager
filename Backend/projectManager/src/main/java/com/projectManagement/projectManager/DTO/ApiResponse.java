package com.projectManagement.projectManager.DTO;

import lombok.Data;

@Data
public class ApiResponse {
    private String Message;
    private Boolean Success;

    public ApiResponse() {
    }

    public ApiResponse(String message, Boolean success) {
        Message = message;
        Success = success;
    }
}
