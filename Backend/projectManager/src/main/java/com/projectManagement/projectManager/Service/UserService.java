package com.projectManagement.projectManager.Service;

import com.projectManagement.projectManager.DTO.UserDTO;
import com.projectManagement.projectManager.Models.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);
    UserDTO createUser (UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, BigInteger userId);

    void deleteUser(BigInteger userId);

    UserDTO getUser(BigInteger userId);

    List<UserDTO> getAllUsers();
}
