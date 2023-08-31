package com.projectManagement.projectManager.CustomValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TenDigitNonZeroStartPhoneNumberValidator.class)
public @interface TenDigitNonZeroStartPhoneNumber {
    String message() default "The phone number should have exactly 10 digits and should not start with zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
