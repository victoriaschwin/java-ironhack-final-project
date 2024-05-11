package com.example.demo.controllers.interfaces;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.List;

public interface IFlightController {
    List<Flight> getFlights();
    Flight addFlight(Flight flight);
    void updateFlight(Integer flightId, Flight flight);
    void deleteFlight(Integer flightId);
    List<Flight> getAllFlightsByPrice(double price);
    List<Flight> getAllFlightsByArrivalAirport(Airport airport);
    List<Flight> getAllFlightsByDepartureAirport(Airport airport);
    List<Flight> getAllFlightsByArrivalTime(Instant arrivalTime);
    List<Flight> getAllFlightsByDepartureTime(Instant departureTime);
}
