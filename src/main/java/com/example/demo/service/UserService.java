package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }
}
