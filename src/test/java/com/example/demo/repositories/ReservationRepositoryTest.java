package com.example.demo.repositories;

import com.example.demo.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;

    private List<User> users;
    private List<Flight> flights;
    private List<Reservation> reservations;
/*
    @BeforeEach
    void setUp(){
        users = new ArrayList<>();
        flights = new ArrayList<>();
        reservations = new ArrayList<>();

        users.add(new User("userOne", "123456"));
        users.add(new User("userTwo", "password"));

        flights.add(new Flight(Airline.BritishAirways, Airport.SFO,Airport.ATL, Instant.parse("2018-12-30T19:34:50.63Z"),Instant.parse("2019-01-01T01:34:50.63Z"),180));
        flights.add(new Flight(Airline.AmericanAirlines, Airport.MIA,Airport.JFK, Instant.parse("2018-12-30T20:34:50.63Z"),Instant.parse("2019-01-01T02:34:50.63Z"),70));
        flights.add(new Flight(Airline.BritishAirways, Airport.SFO, Airport.ATL, Instant.parse("2019-01-02T08:00:00.00Z"),Instant.parse("2019-01-03T03:00:00.00Z"),180 ));

        reservations.add(new Reservation(flights.get(0),users.get(0),2,flights.get(0).getPrice()*2,Instant.parse("2024-04-20T13:20:20.000Z")));
        reservations.add(new Reservation(flights.get(1),users.get(0),1,flights.get(1).getPrice(),Instant.parse("2024-01-12T12:00:00.000Z")));
        reservations.add(new Reservation(flights.get(0),users.get(1),1,flights.get(0).getPrice(),Instant.parse("2024-04-20T13:20:20.000Z")));

        reservationRepository.saveAll(reservations);
    }

    @AfterEach
    void tearUp(){
        reservationRepository.deleteAll(reservations);
    }

    @Test
    void findByIdTrue(){
        Optional<Reservation> maybeReservation = reservationRepository.findById(0);
        if(maybeReservation.isPresent()){
            assertEquals(users.get(0), maybeReservation.get().getUserId());
            assertEquals(flights.get(0),maybeReservation.get().getFlightId());
            assertEquals(360, maybeReservation.get().getPricePaid());
            assertEquals("2024-04-20T13:20:20.000Z",maybeReservation.get().getBookingDate().toString());
            assertEquals(2, maybeReservation.get().getNumberPassengers());
        };
    }
    @Test
    void findByIdFalse(){
        Optional<Reservation> noReservation = reservationRepository.findById(205);
        assertTrue(noReservation.isEmpty());
    }
    @Test
    void findByUserIdShouldReturnTwo(){
        Optional<List<Reservation>> maybeReservations = reservationRepository.findAllByUserId(users.get(0));

        assertTrue(maybeReservations.isPresent());
        assertEquals(2, maybeReservations.get().size());
        assertEquals(users.get(0), maybeReservations.get().get(0).getUserId());
        assertEquals(users.get(0), maybeReservations.get().get(1).getUserId());
    }
    @Test
    void findByUserIdShouldReturnZero(){
        User dummyUser = new User("dummyUser", "testPassword");
        userRepository.save(dummyUser);
        Optional<List<Reservation>> noReservations = reservationRepository.findAllByUserId(dummyUser);

        assertEquals(0,noReservations.get().size());
    }
    @Test
    void findByBookingDateShouldReturnTwo(){
        Optional<List<Reservation>> maybeReservations = reservationRepository.findByBookingDate(Instant.parse("2024-04-20T13:20:20.000Z"));

        assertTrue(maybeReservations.isPresent());
        assertEquals(2, maybeReservations.get().size());
        assertEquals("2024-04-20T13:20:20Z", maybeReservations.get().get(0).getBookingDate().toString());
        assertEquals("2024-04-20T13:20:20Z", maybeReservations.get().get(1).getBookingDate().toString());
    }
    @Test
    void findByBookingDateShouldReturnZero(){
        Optional<List<Reservation>> noReservations = reservationRepository.findByBookingDate(Instant.parse("2020-10-02T00:00:00Z"));
        assertEquals(0, noReservations.get().size());
    }



 */
}
