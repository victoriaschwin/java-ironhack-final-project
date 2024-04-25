package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }
}
