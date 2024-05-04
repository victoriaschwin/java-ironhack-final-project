package com.example.demo.services;


import com.example.demo.models.Flight;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation getReservationById(Integer reservationId){
        return reservationRepository.findById(reservationId).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation not found"));
    }
}


