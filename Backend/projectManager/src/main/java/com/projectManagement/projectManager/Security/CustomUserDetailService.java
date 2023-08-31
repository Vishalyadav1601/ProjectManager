package com.projectManagement.projectManager.Security;


import com.projectManagement.projectManager.DAO.UserDAO;
import com.projectManagement.projectManager.Exceptions.NameResourceNotFoundException;
import com.projectManagement.projectManager.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDAO.findByUserName(username).orElseThrow(()->new NameResourceNotFoundException("User","Name","username"));
        return user;
    }
}
