package com.example.demo.controllers;

import com.example.demo.models.Airline;
import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.models.User;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.FlightService;
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
public class FlightControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightService flightService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Flight flightOne = new Flight(Airline.AmericanAirlines, Airport.ATL,Airport.EWR, Instant.parse("2020-10-10T10:00:20Z"), Instant.parse("2020-10-10T17:00:20Z"),100);
        Flight fligthTwo = new Flight(Airline.AirFrance, Airport.CDG,Airport.MAD,Instant.parse("2024-02-01T05:60:30Z"),Instant.parse("2024-02-01T10:00:00Z"),30);
        flightRepository.saveAll(List.of(flightOne,fligthTwo));
    }

    @AfterEach
    void tearDown(){
        flightRepository.deleteAll();
    }


}
