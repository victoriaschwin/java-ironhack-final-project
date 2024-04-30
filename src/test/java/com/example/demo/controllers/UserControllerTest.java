package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User userOne = new User("userOne", "12345");
        User userTwo = new User("userTwo","newPassword");

        userRepository.saveAll(List.of(userOne,userTwo));
    }
    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void getAllUsersTest() throws Exception{
        List<User> users = userRepository.findAll();
        String expectedJson = objectMapper.writeValueAsString(users);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
    @Test
    void getUserByIdPositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(userService.getUserById(1));
        mockMvc.perform(get("/users/{id}", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getUserByIdNegativeTest() throws Exception{
        mockMvc.perform(get("/users/{id}","14").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void storeValidCreatedUserTest() throws Exception{
        User foundUser;
        User user = new User("patriciaFrern", "password12");
        String body = objectMapper.writeValueAsString(user);

        MvcResult mvcResult = mockMvc.perform(post("/users")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        Optional<User> savedUser = userRepository.findByUsername(user.getUsername());
        if(savedUser.isPresent()){
            foundUser = savedUser.get();
            assertEquals(user.getUsername(), foundUser.getUsername());
            assertEquals(user.getPassword(), foundUser.getPassword());
        }


    }

    @Test
    void deleteUserTest() throws Exception{
        long nbResources = userRepository.count();
        MvcResult mvcResult = mockMvc.perform(delete("/users/{id}","2")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertEquals(userRepository.count(), nbResources-1);
    }
}
