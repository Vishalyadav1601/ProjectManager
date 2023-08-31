package com.projectManagement.projectManager.CustomValidation;

import com.projectManagement.projectManager.DAO.UserDAO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private final UserDAO userDao;

    public UniqueUsernameValidator(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return userName != null && !userDao.existsByUserName(userName);
    }
}