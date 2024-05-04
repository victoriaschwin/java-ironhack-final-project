package com.example.demo.services;


import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.*;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation getReservationById(Integer reservationId){
        return reservationRepository.findById(reservationId).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation not found"));
    }

    public List<Reservation> getAllReservationsByBookingDate(Instant bookingDate){
        return reservationRepository.findByBookingDate(bookingDate).orElseThrow( ()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation with " +bookingDate + " not found"));
    }

    public Reservation addNewReservation(Reservation reservation){ return reservationRepository.save(reservation);}

    public void updateReservation(Integer reservationId, Reservation reservation){
        Optional<Reservation> maybeReservation = reservationRepository.findById(reservationId);
        if (maybeReservation.isPresent()){
            Reservation reservationFound = maybeReservation.get();
            Flight flight = reservation.getFlightId();
            User user = reservation.getUserId();
            Integer numberPassengers = reservation.getNumberPassengers();
            double pricePaid = reservation.getPricePaid();
            Instant bookingDate = reservation.getBookingDate();

            reservationFound.setFlightId(flight);
            reservationFound.setUserId(user);
            reservationFound.setNumberPassengers(numberPassengers);
            reservationFound.setPricePaid(pricePaid);
            reservationFound.setBookingDate(bookingDate);

            reservationRepository.save(reservationFound);
        }else {
            throw new UserNotFoundException("Reservation with id"+ reservationId +"not found");
        }
    }

    public void deleteReservation(Integer reservationId){
        Optional<Reservation> maybeReservation = reservationRepository.findById(reservationId);
        if(maybeReservation.isPresent()){
            reservationRepository.delete(maybeReservation.get());
        }else{
            throw new UserNotFoundException("Reservation with id " + reservationId + " not found");
        }
    }
}


