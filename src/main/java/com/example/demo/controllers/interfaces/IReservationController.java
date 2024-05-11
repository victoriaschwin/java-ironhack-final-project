package com.example.demo.controllers.interfaces;

import com.example.demo.models.Reservation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.List;

public interface IReservationController {

    List<Reservation> getReservations();
    Reservation getReservationById(Integer reservationId);
    List<Reservation> getAllReservationsByBookingDate(String bookingDate);
    Reservation addReservation(Reservation reservation);
    void updateReservation(Integer reservationId,Reservation reservation);
    void deleteReservation(Integer reservationId);
}
