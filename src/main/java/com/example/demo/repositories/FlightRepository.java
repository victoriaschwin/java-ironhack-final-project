package com.example.demo.repositories;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Optional<Flight> findById(Integer flightId);
    Optional<List<Flight>> findAllByArrivalAirport(Airport arrivalAirport);
    Optional<List<Flight>> findAllByDepartureAirport(Airport departureAirport);
    Optional<List<Flight>> findAllByArrivalTime(Instant arrivalTime);
    Optional<List<Flight>> findAllByPrice(double price);
    Optional<List<Flight>> findAllByDepartureTime(Instant departureTime);
    Optional<List<Flight>> findAllByArrivalAirportAndDepartureAirport(Airport arrivalAirport, Airport departureAirport);
    Optional<List<Flight>> findAllByArrivalTimeAndDepartureTime(Instant arrivalTime, Instant departureTime);

    //TODO add method to search by time range
}
