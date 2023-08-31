package com.projectManagement.projectManager.CustomValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigInteger;

public class TenDigitNonZeroStartPhoneNumberValidator implements ConstraintValidator<TenDigitNonZeroStartPhoneNumber, BigInteger> {
    @Override
    public void initialize(TenDigitNonZeroStartPhoneNumber constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(BigInteger value, ConstraintValidatorContext context) {

        String phoneNumberString = value.toString();
        if (phoneNumberString.length() != 10) {
            return false;
        }

        if (phoneNumberString.startsWith("0")) {
            return false;
        }
        return true;
    }
}

