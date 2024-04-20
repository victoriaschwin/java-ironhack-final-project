package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Integer userId){
        try{
            return userRepository.findById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by ID: " + userId, e);
        }
    }
}
