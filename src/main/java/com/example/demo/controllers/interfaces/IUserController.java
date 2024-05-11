package com.example.demo.controllers.interfaces;

import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserController {
    List<User> getUsers();
    User getUserById(Integer userId);
    User addUser(User user);
    void updateUser(Integer userId,User user );
    void deleteUser(Integer userId);
    User login(User user);
}
