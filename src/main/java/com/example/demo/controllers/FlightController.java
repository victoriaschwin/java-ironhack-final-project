package com.example.demo.controllers;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getFlights(){ return flightRepository.findAll();}

    @PostMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@RequestBody Flight flight){
        return flightService.addNewFlight(flight);
    }

    @PatchMapping("/flights/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateFlight(@PathVariable(name = "id") Integer flightId, @RequestBody Flight flight){
        flightService.updateFlight(flightId, flight);
    }

    @DeleteMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFlight(@PathVariable(name = "id") Integer flightId){
        flightService.deleteFlight(flightId);
    }

    @GetMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flight getFlightById(@PathVariable(name = "id") Integer flightId){
        return  flightService.getFlightById(flightId);
    }

    @GetMapping("/flights/byPrice")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlightsByPrice(@RequestParam(name = "price") double price){
        return flightService.getAllFlightsByPrice(price);
    }

    @GetMapping("/flights/byArrivalAirport")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlightsByArrivalAirport(@RequestParam(name = "arrivalAirport")Airport airport){
        return flightService.getAllFlightsByArrivalAirport(airport);
    }
}
