package com.example.demo.controllers.implementations;

import com.example.demo.controllers.interfaces.IUserController;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.implementations.UserService;
import com.example.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController implements IUserController {
    @Autowired
    private IUserService userService;

    @Override
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public User login(@RequestBody User user){
        return userService.getUserByUsername(user.getUsername());
    }

    @Override
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){return userService.getUsers();}

    @Override
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable(name = "id") Integer userId){
            return userService.getUserById(userId);
    }

    @Override
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

    @Override
    @PatchMapping("/users/user/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable(name = "id") Integer userId, @RequestBody User user ){
        userService.updateUser(userId, user);
    }

    @Override
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable(name = "id") Integer userId){
        userService.deleteUser(userId);
    }

}
