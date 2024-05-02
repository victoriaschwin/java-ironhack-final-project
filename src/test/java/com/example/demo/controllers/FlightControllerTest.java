package com.example.demo.controllers;

import com.example.demo.models.Airline;
import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.FlightService;
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

import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Flight fligthTwo = new Flight(Airline.AirFrance, Airport.CDG,Airport.MAD,Instant.parse("2024-02-01T05:40:30Z"),Instant.parse("2024-02-01T10:00:00Z"),30);
        flightRepository.saveAll(List.of(flightOne,fligthTwo));
    }

    @AfterEach
    void tearDown(){
        flightRepository.deleteAll();
    }

    @Test
    void getAllFlightsTest() throws Exception{
        List<Flight> flights = flightRepository.findAll();
        String expectedJson = objectMapper.writeValueAsString(flights);

        mockMvc.perform(get("/flights").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

}
