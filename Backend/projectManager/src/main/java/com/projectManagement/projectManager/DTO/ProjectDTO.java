package com.projectManagement.projectManager.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Data
public class ProjectDTO {
    private BigInteger projectId;
    @NotNull(message = "Project Number is Invalid")
    private int projectNumber;
    @NotEmpty(message = "Project Title is Invalid")
    private String projectTitle;
    @NotEmpty(message = "Project Content is Invalid")
    private String projectContent;
    private UserDTO user;
}
