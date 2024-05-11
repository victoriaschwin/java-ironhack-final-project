package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.services.implementations.ReservationService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
/*
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User userOne = new User("userTest", "password320");
        User userTwo = new User("userTwo", "password12");
        Flight flightOne = new Flight(Airline.Lufthansa, Airport.HND,Airport.DXB, Instant.parse("2024-05-12T12:30:00Z"),Instant.parse("2024-05-12T23:30:00Z"),78);
        Reservation reservationOne = new Reservation(flightOne,userOne,2, flightOne.getPrice()*2, Instant.parse("2024-05-12T23:30:00Z"));
        Reservation reservationTwo = new Reservation(flightOne,userTwo,1, flightOne.getPrice(), Instant.parse("2024-05-12T23:30:00Z"));

        reservationRepository.saveAll(List.of(reservationOne,reservationTwo));
    }
    @AfterEach
    void tearDown(){
        reservationRepository.deleteAll();
    }

    @Test
    void getReservationsTest() throws Exception{
        List<Reservation> reservations = reservationRepository.findAll();
        String expectedJson = objectMapper.writeValueAsString(reservations);

        mockMvc.perform(get("/reservations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void getReservationByIdPositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(reservationRepository.findById(1));
        mockMvc.perform(get("/reservations/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void getReservationByIdNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/reservations/{id}","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void getAllReservationsByBookingDatePositiveResponseTest() throws Exception{
        String expectedJson = objectMapper.writeValueAsString(reservationRepository.findByBookingDate(Instant.parse("2024-05-12T23:30:00Z")));
        mockMvc.perform(get("/reservations/byBookingDate")
                        .param("bookingDate","2024-05-12T23:30:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFlightsByPriceNegativeResponseTest() throws Exception{
        mockMvc.perform(get("/reservations/byBookingDate")
                        .param("bookingDate","2024-05-12T23:30:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(status().reason(containsString("not found")));
    }

    @Test
    void storeValidCreatedReservationTest() throws Exception{
        Reservation reservationFound;
        User user = new User("user", "password");
        Flight flight = new Flight(Airline.BritishAirways,Airport.HND,Airport.EWR,Instant.now(),Instant.parse("2024-10-10T00:00:00Z"),100);
        Reservation reservation = new Reservation(flight,user,1,flight.getPrice(),Instant.parse("2024-11-10T00:00:00Z"));
        String body = objectMapper.writeValueAsString(reservation);

        MvcResult mvcResult = mockMvc.perform(post("/reservations")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        Optional<List<Reservation>> savedReservations = reservationRepository.findByBookingDate(Instant.parse("2024-11-10T00:00:00Z"));
        if (savedReservations.isPresent()){
            reservationFound = savedReservations.get().get(0);
            assertEquals(reservation.getNumberPassengers(), reservationFound.getNumberPassengers());
            assertEquals(reservation.getBookingDate(), reservationFound.getBookingDate());
        }
    }

 */
}
