package com.example.demo.services;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.models.User;
import com.example.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Optional<Flight> findById(Integer flightId){
        try{
            return flightRepository.findById(flightId);
        } catch (Exception e) {
            throw new RuntimeException("Error finding flight by ID: " + flightId, e);
        }
    }

    public Optional<List<Flight>> findAllByArrivalAirport(Airport arrivalAirport){
        try{
            return flightRepository.findAllByArrivalAirport(arrivalAirport);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by Airport of Arrival: " + arrivalAirport, e);
        }
    }

    public Optional<List<Flight>> findAllByDepartureAirport( Airport departureAirport){
        try {
            return flightRepository.findAllByDepartureAirport(departureAirport);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by Airport of Departure: " + departureAirport, e);
        }
    }

    public Optional<List<Flight>> findAllByArrivalTime(Instant arrivalTime){
        try {
            return flightRepository.findAllByArrivalTime(arrivalTime);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by time of arrival: " + arrivalTime, e);
        }
    }

    public Optional<List<Flight>> findAllByPrice(double price){
        try {
            return flightRepository.findAllByPrice(price);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by price: " + price, e);
        }
    }

    public Optional<List<Flight>> findAllByDepartureTime(Instant departureTime){
        try {
            return flightRepository.findAllByDepartureTime(departureTime);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by time of departure: " + departureTime, e);
        }
    }

    public Optional<List<Flight>> findAllByArrivalAirportAndDepartureAirport(Airport arrivalAirport, Airport departureAirport){
        try{
            return flightRepository.findAllByArrivalAirportAndDepartureAirport(arrivalAirport,departureAirport);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by airports: " + arrivalAirport +" "+ departureAirport, e);
        }
    }

    public Optional<List<Flight>> findAllByArrivalTimeAndDepartureTime(Instant arrivalTime, Instant departureTime){
        try {
            return flightRepository.findAllByArrivalTimeAndDepartureTime(arrivalTime,departureTime);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by ID: " + arrivalTime+ " "+ departureTime, e);
        }
    }
}
