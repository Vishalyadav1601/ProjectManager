package com.projectManagement.projectManager.Exceptions;


import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class IdResourceNotFoundException extends RuntimeException{
        String resourceName;
        String fieldName;
        BigInteger fieldValue;

        public IdResourceNotFoundException(String resourceName, String fieldName, BigInteger fieldValue) {
            super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
            this.resourceName = resourceName;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

}
