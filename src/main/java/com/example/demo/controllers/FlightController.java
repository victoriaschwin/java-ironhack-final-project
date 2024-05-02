package com.example.demo.controllers;

import com.example.demo.models.Flight;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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


}
