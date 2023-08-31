package com.projectManagement.projectManager.Service;

import com.projectManagement.projectManager.Config.AppConstants;
import com.projectManagement.projectManager.DAO.RoleDAO;
import com.projectManagement.projectManager.DAO.UserDAO;
import com.projectManagement.projectManager.DTO.UserDTO;
import com.projectManagement.projectManager.Exceptions.IdResourceNotFoundException;
import com.projectManagement.projectManager.Models.Role;
import com.projectManagement.projectManager.Models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = this.DTOToUser(userDTO);
        user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));
        Role role = this.roleDAO.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userDAO.save(user);
        return this.UserToDTO(newUser);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.DTOToUser(userDTO);
        User createdUser = this.userDAO.save(user);
        return this.UserToDTO(createdUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, BigInteger userId) {
        User user = this.userDAO.findById(userId).orElseThrow(()->new IdResourceNotFoundException("User","Id",userId));
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserPhone(userDTO.getUserPhone());
        User updatedUser = this.userDAO.save(user);
        return this.UserToDTO(updatedUser);
    }

    @Override
    public void deleteUser(BigInteger userId) {
        User user = this.userDAO.findById(userId).orElseThrow(()->new IdResourceNotFoundException("User","Id",userId));
        this.userDAO.delete(user);
    }

    @Override
    public UserDTO getUser(BigInteger userId) {
        User user = this.userDAO.findById(userId).orElseThrow(()->new IdResourceNotFoundException("User","Id",userId));
        return this.UserToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> user = this.userDAO.findAll();
        List<UserDTO> userDTOS = user.stream().map(User->UserToDTO(User)).collect(Collectors.toList());
        return userDTOS;
    }

    private UserDTO UserToDTO(User user)
    {
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

    private User DTOToUser(UserDTO userDTO)
    {
        User user = this.modelMapper.map(userDTO,User.class);
        return user;
    }
}
