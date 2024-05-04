package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Airline;
import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.models.User;
import com.example.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight getFlightById(Integer flightId){
            return flightRepository.findById(flightId).orElseThrow( ()->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight not found"));
    }

    public Flight addNewFlight(Flight flight){ return flightRepository.save(flight);}
    public Optional<List<Flight>> findAllByArrivalAirport(Airport arrivalAirport){
        try{
            return flightRepository.findAllByArrivalAirport(arrivalAirport);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by Airport of Arrival: " + arrivalAirport, e);
        }
    }

    public void updateFlight(Integer flightId, Flight flight){
        Optional<Flight> maybeFlight = flightRepository.findById(flightId);
        if (maybeFlight.isPresent()){
            Flight flightFound = maybeFlight.get();
            Airline airline = flight.getAirline();
            Airport departureAirport = flight.getDepartureAirport();
            Airport arrivalAirport = flight.getArrivalAirport();
            Instant departureTime = flight.getDepartureTime();
            Instant arrivalTime = flight.getArrivalTime();
            double price = flight.getPrice();

            flightFound.setAirline(airline);
            flightFound.setDepartureAirport(departureAirport);
            flightFound.setArrivalAirport(arrivalAirport);
            flightFound.setDepartureTime(departureTime);
            flightFound.setArrivalTime(arrivalTime);
            flightFound.setPrice(price);

            flightRepository.save(flightFound);
        }else {
            throw new UserNotFoundException("Flight with id"+ flightId +"not found");
        }
    }

    public void deleteFlight(Integer flightId){
        Optional<Flight> maybeFlight = flightRepository.findById(flightId);
        if(maybeFlight.isPresent()){
            flightRepository.delete(maybeFlight.get());
        }else{
            throw new UserNotFoundException("Flight with id " + flightId + " not found");
        }
    }

    public List<Flight> getAllFlightsByPrice(double price){
        return flightRepository.findAllByPrice(price).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight with " +price + " not found"));
    }

    public List<Flight> getAllFlightsByArrivalAirport(Airport airport){
        return flightRepository.findAllByArrivalAirport(airport).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight with " +airport + " not found"));
    }

    public List<Flight> getAllFlightsByDepartureAirport(Airport airport){
        return flightRepository.findAllByDepartureAirport(airport).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight with " +airport + " not found"));
    }

    public List<Flight> getAllFlightsByArrivalTime(Instant arrivalTime){
        return flightRepository.findAllByArrivalTime(arrivalTime).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight with " +arrivalTime + " not found"));
    }

    public List<Flight> getAllFlightsByDepartureTime(Instant departureTime){
        return flightRepository.findAllByDepartureTime(departureTime).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight with " +departureTime + " not found"));
    }
}

