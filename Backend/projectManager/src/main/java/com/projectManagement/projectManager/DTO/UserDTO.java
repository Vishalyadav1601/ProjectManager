package com.projectManagement.projectManager.DTO;

import com.projectManagement.projectManager.CustomValidation.TenDigitNonZeroStartPhoneNumber;
import com.projectManagement.projectManager.CustomValidation.UniqueUsername;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class UserDTO {
    private BigInteger userId;
    @NotEmpty(message = "Invalid User Name")
    @UniqueUsername(message = "User Name already exists")
    private String userName;
    @NotEmpty(message = "Invlalid User Password")
    private String userPassword;
    @NotEmpty(message = "Invalid User Email")
    private String userEmail;
    @TenDigitNonZeroStartPhoneNumber(message = "User Phone Number must have 10 Digits and must not start with Zero")
    private BigInteger userPhone;

    private Set<RoleDTO> roles =new HashSet<>();
}
