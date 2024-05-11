package com.example.demo.services.interfaces;

import com.example.demo.models.Reservation;

import java.time.Instant;
import java.util.List;

public interface IReservationService {

   Reservation getReservationById(Integer reservationId);
    List<Reservation> getAllReservationsByBookingDate(Instant bookingDate);
    Reservation addNewReservation(Reservation reservation);
    void updateReservation(Integer reservationId, Reservation reservation);
    void deleteReservation(Integer reservationId);

}
