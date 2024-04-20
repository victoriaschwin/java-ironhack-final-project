package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    static Flight flightTest;
    static Flight flightTestTwo;

    @BeforeEach
    void setUp(){
        Instant timestamp = Instant.now();
        Duration hours = Duration.ofHours(8);
        Instant arrivalTimestamp = timestamp.plus(hours);

        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight(Airline.BritishAirways,Airport.LAX,Airport.SIN,timestamp,arrivalTimestamp,80));
        flightList.add(new Flight(Airline.Lufthansa,Airport.ATL, Airport.MIA,timestamp,arrivalTimestamp,50));

        //flightTest = new Flight(Airline.AirCanada,Airport.CDG,Airport.DXB,timestamp,arrivalTimestamp,150,flightList);
        flightTestTwo = new Flight(Airline.AirFrance,Airport.CGK,Airport.EWR,timestamp,arrivalTimestamp,40);
    }

    @Test
    public void createEmptyFlight(){
        Flight emptyFlight = new Flight();
        assertNotNull(emptyFlight);
        assertEquals(null, emptyFlight.getAirline());
    }

   /* @Test
    public void createFlight(){
        assertEquals(150, flightTest.getPrice());
        assertEquals(Airline.AirCanada, flightTest.getAirline());
        assertNotNull(flightTest);
    }

    @Test
    public void shouldBeCorrectlyInstantiated(){
        assertEquals(Airline.AirCanada,flightTest.getAirline());
        assertEquals(Airport.CDG,flightTest.getDepartureAirport());
        assertEquals(Airport.DXB, flightTest.getArrivalAirport());
        assertNotNull(flightTest.getDepartureTime());
        assertNotNull(flightTest.getArrivalTime());

    }

    */
}
