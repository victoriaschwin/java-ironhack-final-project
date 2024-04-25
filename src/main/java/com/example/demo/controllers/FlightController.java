package com.example.demo.controllers;

import com.example.demo.services.FlightService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

}
