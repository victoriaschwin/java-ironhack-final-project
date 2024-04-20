package com.example.demo.repositories;

import com.example.demo.models.Airline;
import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp(){
        //0-British, SFO, ATL, 2018-12-30T19:34:50.63Z, 2019-01-01T01:34:50.630000Z, 180
        //1- American, MIA, JFK, 2018-12-30T20:34:50.63Z, 2019-01-01T02:34:50.63Z, 70
        //2- British, SFO, ATL, 2019-01-02T08:00:00.00Z,  2019-01-03T03:00:00.00Z, 180
        //3- AirCanada, MIA, JFK, 2018-12-30T20:34:50.63Z, 2019-01-01T02:34:50.63Z, 90
        List<Flight> flights = new ArrayList<>();

        flights.add(new Flight(Airline.BritishAirways, Airport.SFO,Airport.ATL,Instant.parse("2018-12-30T19:34:50.63Z"),Instant.parse("2019-01-01T01:34:50.63Z"),180));
        flights.add(new Flight(Airline.AmericanAirlines, Airport.MIA,Airport.JFK, Instant.parse("2018-12-30T20:34:50.63Z"),Instant.parse("2019-01-01T02:34:50.63Z"),70));
        flights.add(new Flight(Airline.BritishAirways, Airport.SFO, Airport.ATL, Instant.parse("2019-01-02T08:00:00.00Z"),Instant.parse("2019-01-03T03:00:00.00Z"),180 ));
        flights.add(new Flight(Airline.AirCanada,Airport.MIA,Airport.JFK, Instant.parse("2018-12-30T20:34:50.63Z"),Instant.parse("2019-01-01T02:34:50.63Z"),90));

        flightRepository.saveAll(flights);
    }

    @AfterEach
    void tearUp(){
        flightRepository.deleteAll();
    }

    @Test
    void findByIdTrue(){
       Optional<Flight> maybeFlight = flightRepository.findById(0);
       if(maybeFlight.isPresent()){
           assertEquals(Airline.BritishAirways, maybeFlight.get().getAirline());
           assertEquals(Airport.SFO,maybeFlight.get().getDepartureAirport());
           assertEquals(Airport.ATL, maybeFlight.get().getArrivalAirport());
           assertEquals("2018-12-30T19:34:50.63Z",maybeFlight.get().getDepartureTime().toString());
           assertEquals("2019-01-01T01:34:50.630000Z", maybeFlight.get().getArrivalTime().toString());
           assertEquals(180, maybeFlight.get().getPrice());
       };
    }
    @Test
    void findByIdFalse(){
        Optional<Flight> noFlight = flightRepository.findById(205);
        assertTrue(noFlight.isEmpty());
    }
    @Test
    void findAllByArrivalAirportShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByArrivalAirport(Airport.JFK);

        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals(Airport.JFK, maybeFlights.get().get(0).getArrivalAirport());
        assertEquals(Airport.JFK, maybeFlights.get().get(1).getArrivalAirport());
    }
    @Test
    void findAllByArrivalAirportShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByArrivalAirport(Airport.EWR);
        assertEquals(0,noFlights.get().size());
    }
    @Test
    void findAllByDepartureAirportShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByDepartureAirport(Airport.MIA);

        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals(Airport.MIA, maybeFlights.get().get(0).getDepartureAirport());
        assertEquals(Airport.MIA, maybeFlights.get().get(1).getDepartureAirport());
    }
    @Test
    void findAllByDepartureAirportShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByDepartureAirport(Airport.SIN);
        assertEquals(0,noFlights.get().size());
    }
    @Test
    void findAllByArrivalTimeShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByArrivalTime(Instant.parse("2019-01-01T02:34:50.63Z"));

        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals("2019-01-01T02:34:50.630Z", maybeFlights.get().get(0).getArrivalTime().toString());
        assertEquals("2019-01-01T02:34:50.630Z", maybeFlights.get().get(1).getArrivalTime().toString());
    }
    @Test
    void findAllByArrivalTimeShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByArrivalTime(Instant.now());
        assertEquals(0,noFlights.get().size());
    }
    @Test
    void findAllByPriceShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByPrice(180);
        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals(180, maybeFlights.get().get(0).getPrice());
        assertEquals(180, maybeFlights.get().get(1).getPrice());
    }
    @Test
    void findAllByPriceShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByPrice(20);
        assertEquals(0,noFlights.get().size());
    }
    @Test
    void findAllByDepartureTimeShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByDepartureTime(Instant.parse("2018-12-30T20:34:50.63Z"));

        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals("2018-12-30T20:34:50.630Z", maybeFlights.get().get(0).getDepartureTime().toString());
        assertEquals("2018-12-30T20:34:50.630Z", maybeFlights.get().get(1).getDepartureTime().toString());
    }
    @Test
    void findAllByDepartureTimeShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByDepartureTime(Instant.now());
        assertEquals(0,noFlights.get().size());
    }
    @Test
    void findAllByArrivalAirportAndDepartureAirportShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByArrivalAirportAndDepartureAirport(Airport.ATL,Airport.SFO);

        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals(Airport.SFO, maybeFlights.get().get(0).getDepartureAirport());
        assertEquals(Airport.ATL, maybeFlights.get().get(0).getArrivalAirport());
        assertEquals(Airport.SFO, maybeFlights.get().get(1).getDepartureAirport());
        assertEquals(Airport.ATL, maybeFlights.get().get(1).getArrivalAirport());
    }
    @Test
    void findAllByArrivalAirportAndDepartureAirportShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByArrivalAirportAndDepartureAirport(Airport.EWR, Airport.DEL);
        assertEquals(0,noFlights.get().size());
    }
    @Test
    void findAllByArrivalTimeAndDepartureTimeShouldReturnTwo(){
        Optional<List<Flight>> maybeFlights = flightRepository.findAllByArrivalTimeAndDepartureTime(Instant.parse("2019-01-01T02:34:50.63Z"), Instant.parse("2018-12-30T20:34:50.63Z"));

        assertTrue(maybeFlights.isPresent());
        assertEquals(2, maybeFlights.get().size());
        assertEquals("2018-12-30T20:34:50.630Z", maybeFlights.get().get(0).getDepartureTime().toString());
        assertEquals("2019-01-01T02:34:50.630Z", maybeFlights.get().get(0).getArrivalTime().toString());
        assertEquals("2018-12-30T20:34:50.630Z", maybeFlights.get().get(1).getDepartureTime().toString());
        assertEquals("2019-01-01T02:34:50.630Z", maybeFlights.get().get(1).getArrivalTime().toString());
    }
    @Test
    void findAllByArrivalTimeAndDepartureTimeShouldReturnNull(){
        Optional<List<Flight>> noFlights = flightRepository.findAllByArrivalTimeAndDepartureTime(Instant.now(), Instant.now().plus(1,ChronoUnit.DAYS));
        assertEquals(0,noFlights.get().size());
    }

}
