package com.example.demo.controllers.implementations;

import com.example.demo.controllers.interfaces.IFlightController;
import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.implementations.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController implements IFlightController {
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

    @GetMapping("/flights/byDepartureAirport")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlightsByDepartureAirport(@RequestParam(name = "departureAirport")Airport airport){
        return flightService.getAllFlightsByDepartureAirport(airport);
    }

    @GetMapping("/flights/byArrivalTime")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlightsByArrivalTime(@RequestParam(name = "arrivalTime")Instant arrivalTime){
        return flightService.getAllFlightsByArrivalTime(arrivalTime);
    }

    @GetMapping("/flights/byDepartureTime")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlightsByDepartureTime(@RequestParam(name = "departureTime")Instant departureTime){
        return flightService.getAllFlightsByDepartureTime(departureTime);
    }
}
