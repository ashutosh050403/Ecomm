package com.vision.ecomm.controller;


import com.vision.ecomm.model.User;
import com.vision.ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    private User loginUser(@RequestBody User user) {
        return userService.loginUser(user.getEmail(),user.getPassword());
    }

    @GetMapping
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
