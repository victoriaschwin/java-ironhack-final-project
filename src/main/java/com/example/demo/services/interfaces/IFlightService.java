package com.example.demo.services.interfaces;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface IFlightService {
    Flight getFlightById(Integer flightId);
    Flight addNewFlight(Flight flight);
    void updateFlight(Integer flightId, Flight flight);
    void deleteFlight(Integer flightId);
    List<Flight> getAllFlightsByPrice(double price);
    List<Flight> getAllFlightsByArrivalAirport(Airport airport);
    List<Flight> getAllFlightsByDepartureAirport(Airport airport);
    List<Flight> getAllFlightsByArrivalTime(Instant arrivalTime);
    List<Flight> getAllFlightsByDepartureTime(Instant departureTime);
}
