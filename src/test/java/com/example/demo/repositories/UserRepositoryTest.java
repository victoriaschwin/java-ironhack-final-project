package com.example.demo.repositories;

import com.example.demo.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    static User testUser;

    @BeforeEach
    void setUp(){
        testUser = new User("userOne", "password1234");

        userRepository.save(testUser);
    }

    @AfterEach
    void tearDown(){
        userRepository.delete(testUser);
    }

    @Test
    void userByIdTrue(){
        Optional<User> maybeUser = userRepository.findById(1);
        if(maybeUser.isPresent()){
            assertEquals("userOne", maybeUser.get().getUsername());
            assertEquals("password1234", maybeUser.get().getPassword());
        }
    }

    @Test
    void userByIdFalse(){
        Optional<User> maybeUser = userRepository.findById(100);
        assertFalse(maybeUser.isPresent());
    }
}
