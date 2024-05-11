package com.example.demo.services.interfaces;

import com.example.demo.models.Role;
import com.example.demo.models.User;

import java.util.List;

public interface IUserService {
    User getUserById(Integer userId);
    User getUserByUsername(String username);
    User addNewUser(User user);
    Role addNewRole(Role role);
    void updateUser(Integer userId, User user);
    void deleteUser(Integer userId);
    void addRoleToUser(String username, String roleName);
    List<User> getUsers();

}
