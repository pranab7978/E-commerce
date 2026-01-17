package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Error: Email already exists!";
        }
        userRepository.save(user);
        return "Success: User registered!";
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}