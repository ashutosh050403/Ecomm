package com.vision.ecomm.service;


import com.vision.ecomm.model.User;
import com.vision.ecomm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        try {
            User newUser = userRepository.save(user);
            System.out.println("User added to the Database..");
            return newUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;// invalid credentials
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
