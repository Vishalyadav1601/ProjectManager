package com.projectManagement.projectManager.Controller;


import com.projectManagement.projectManager.DTO.JWTRequest;
import com.projectManagement.projectManager.DTO.JWTResponse;
import com.projectManagement.projectManager.DTO.UserDTO;
import com.projectManagement.projectManager.Exceptions.ApiException;
import com.projectManagement.projectManager.Security.JWTTokenHelper;
import com.projectManagement.projectManager.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request)
    {
        this.doAuthenticate(request.getUserName(),request.getUserPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String userName,String userPassword)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,userPassword);
        try{
            authenticationManager.authenticate(authenticationToken);
        }
        catch(BadCredentialsException e)
        {
            throw new ApiException("Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler()
    {
        return "Credentials Invalid!!";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser (@RequestBody UserDTO userDTO)
    {
        UserDTO registeredUser = this.userService.registerUser(userDTO);

        return new ResponseEntity<UserDTO>(registeredUser,HttpStatus.CREATED);
    }
}
