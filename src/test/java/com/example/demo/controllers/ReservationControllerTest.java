package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.List;

@SpringBootTest
public class ReservationControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User userOne = new User("userTest", "password320");
        User userTwo = new User("userTwo", "password12");
        Flight flightOne = new Flight(Airline.Lufthansa, Airport.HND,Airport.DXB, Instant.parse("2024-05-12T12:30:00Z"),Instant.parse("2024-05-12T23:30:00Z"),78);
        Reservation reservationOne = new Reservation(flightOne,userOne,2, flightOne.getPrice()*2, Instant.now());
        Reservation reservationTwo = new Reservation(flightOne,userTwo,1, flightOne.getPrice(), Instant.now());

        reservationRepository.saveAll(List.of(reservationOne,reservationTwo));
    }
    @AfterEach
    void tearDown(){
        reservationRepository.deleteAll();
    }
}
