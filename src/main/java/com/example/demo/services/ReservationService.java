package com.example.demo.services;

import com.example.demo.models.Reservation;
import com.example.demo.models.User;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Optional<Reservation> findById(Integer reservationId){
        try {
            return reservationRepository.findById(reservationId);
        }catch (Exception e){
            throw new RuntimeException("Error finding reservation by ID: " + reservationId, e);
        }
    }

    public Optional<List<Reservation>> findAllByUserId(User userId){
        try {
            return reservationRepository.findAllByUserId(userId);
        }catch (Exception e){
            throw new RuntimeException("Error finding reservation by user ID: " + userId, e);
        }
    }

    public Optional<List<Reservation>> findByBookingDate(Instant bookingDate){
        try {
            return reservationRepository.findByBookingDate(bookingDate);
        }catch (Exception e){
            throw new RuntimeException("Error finding flight by ID: " + bookingDate.toString(), e);
        }
    }
}


