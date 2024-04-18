package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    static User userTest;
    static User userTestTwo;
    @BeforeEach
    void setUp(){
        userTest = new User("dummyuser","strongPassword123!");
        userTestTwo = new User("dummyuser", "weak1234");
    }

    @Test
    public void createEmptyUser(){
        User emptyUser = new User();
        assertNotNull(emptyUser);
        assertEquals(null, emptyUser.getUsername());
    }
    @Test
    public void checkUserIsCorrect(){
        assertEquals("dummyuser", userTest.getUsername());
        assertEquals("weak1234", userTestTwo.getPassword());
    }

    @Test
    public void usernameSetterTest(){
        userTest.setUsername("newUser");
        assertEquals("newUser",userTest.getUsername());
    }

    @Test
    public void passwordGetterTest(){
        assertEquals("strongPassword123!", userTest.getPassword());
    }

}
