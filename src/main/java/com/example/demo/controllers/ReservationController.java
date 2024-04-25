package com.example.demo.controllers;

import com.example.demo.repositories.ReservationRepository;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
}
