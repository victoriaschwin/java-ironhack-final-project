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
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }
    public User addNewUser(User user){
        return userRepository.save(user);
    }

    public void updateUser(Integer userId, User user){
        Optional<User> maybeUser = userRepository.findById(userId);
        if(maybeUser.isPresent()){
            User userFound = maybeUser.get();
            String username = user.getUsername();
            String password = user.getPassword();
            userFound.setUsername(username);
            userFound.setPassword(password);
            userRepository.save(userFound);
        }else{
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
    }
}
