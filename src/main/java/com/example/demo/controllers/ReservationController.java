package com.example.demo.controllers;

import com.example.demo.models.Flight;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservations(){ return reservationRepository.findAll();}

    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservationById(@PathVariable(name = "id") Integer reservationId){
        return  reservationService.getReservationById(reservationId);
    }

    @GetMapping("/flights/byBookingDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservationsByBookingDate(@RequestParam(name = "bookingDate")Instant bookingDate){
        return reservationService.getAllReservationsByBookingDate(bookingDate);
    }
}
