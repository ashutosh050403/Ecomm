package com.vision.ecomm.controller;

import com.vision.ecomm.dto.AuthenticationRequest;
import com.vision.ecomm.dto.AuthenticationResponse;
import com.vision.ecomm.model.User;
import com.vision.ecomm.service.AuthenticationService;
import com.vision.ecomm.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public AuthenticationResponse registerUser(@RequestBody User user) {
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}