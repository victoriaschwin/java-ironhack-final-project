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
import java.util.Optional;

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
    void getFlightsTest() throws Exception{
        List<Flight> flights = flightRepository.findAll();
        String expectedJson = objectMapper.writeValueAsString(flights);

        mockMvc.perform(get("/flights")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void storeValidCreatedFlightTest() throws Exception{
        Flight flightFound;
        Flight flight = new Flight(Airline.BritishAirways,Airport.HND,Airport.EWR,Instant.now(),Instant.parse("2024-10-10T00:00:00Z"),100);
        String body = objectMapper.writeValueAsString(flight);

        MvcResult mvcResult = mockMvc.perform(post("/flights")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        Optional<Flight> savedFlight = flightRepository.findByAirline(flight.getAirline());
        if (savedFlight.isPresent()){
            flightFound = savedFlight.get();
            assertEquals(flight.getPrice(), flightFound.getPrice());
            assertEquals(flight.getAirline(), flightFound.getAirline());
        }
    }
    //TODO add update and delete test
    @Test
    void getFlightByIdPositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(flightRepository.findById(1));
        mockMvc.perform(get("/flights/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


    @Test
    void getFlightByIdNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/flights/{id}","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void getAllFlightsByPricePositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(flightRepository.findAllByPrice(100));
        mockMvc.perform(get("/flights/byPrice")
                        .param("price","100")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void getAllFlightsByPriceNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/flights/byPrice")
                        .param("price","100")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is(400))
                        .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void getAllFlightsByArrivalAirportPositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(flightRepository.findAllByArrivalAirport(Airport.EWR));
        mockMvc.perform(get("/flights/byArrivalAirport")
                        .param("arrivalAirport","EWR")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFlightsByArrivalAirportNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/flights/byArrivalAirport")
                        .param("arrivalAirport","JFK")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void getAllFlightsByDepartureAirportPositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(flightRepository.findAllByDepartureAirport(Airport.ATL));
        mockMvc.perform(get("/flights/byDepartureAirport")
                        .param("departureAirport","ATL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFlightsByDepartureAirportNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/flights/byDepartureAirport")
                        .param("departureAirport","JFK")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void getAllFlightsByArrivalTimePositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(flightRepository.findAllByArrivalTime(Instant.parse("2020-10-10T17:00:20Z")));
        mockMvc.perform(get("/flights/byArrivalTime")
                        .param("arrivalTime","2020-10-10T17:00:20Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFlightsByArrivalTimeNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/flights/byArrivalTime")
                        .param("arrivalTime","1998-10-10T17:00:20Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void getAllFlightsByDepartureTimePositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(flightRepository.findAllByDepartureTime(Instant.parse("2020-10-10T10:00:20Z")));
        mockMvc.perform(get("/flights/byDepartureTime")
                        .param("departureTime","2020-10-10T17:00:20Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFlightsByDepartureTimeNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/flights/byDepartureTime")
                        .param("departureTime","1998-10-10T17:00:20Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }
}
