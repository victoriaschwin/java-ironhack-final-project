package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservationTest {

    static User userTest;
    static Flight flightTest;
    static Reservation reservationTest;
    @BeforeEach
    void setUp(){
        userTest = new User("dummyuser","strongPassword123!");
        flightTest = new Flight(Airline.AmericanAirlines,Airport.ATL,Airport.SFO, Instant.parse("2020-10-01T00:00:00Z"),Instant.parse("2020-10-01T23:00:00Z"),100);
        reservationTest = new Reservation(flightTest,userTest,2, flightTest.getPrice()*2,Instant.parse("2020-06-30T20:00:00Z"));
    }

    @Test
    public void createEmptyReservation(){
        Reservation emptyReservation = new Reservation();
        assertNotNull(emptyReservation);
        assertEquals(null, emptyReservation.getNumberPassengers());
    }
    @Test
    public void checkReservationIsCorrect(){
        assertEquals(flightTest,reservationTest.getFlightId());
        assertEquals(userTest,reservationTest.getUserId());
        assertEquals(2,reservationTest.getNumberPassengers());
        assertEquals(flightTest.getPrice()*2,reservationTest.getPricePaid());
        assertEquals("2020-06-30T20:00:00Z",reservationTest.getBookingDate().toString());
    }
    @Test
    public void numberPassengersSetterTest(){
        reservationTest.setNumberPassengers(3);
        assertEquals(3,reservationTest.getNumberPassengers());
    }
}
