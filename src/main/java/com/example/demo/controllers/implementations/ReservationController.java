package com.example.demo.controllers.implementations;

import com.example.demo.controllers.interfaces.IReservationController;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.services.implementations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController implements IReservationController {
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

    @GetMapping("/reservations/byBookingDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservationsByBookingDate(@RequestParam(name = "bookingDate")String bookingDate){
        //TODO handle the time format to be accepted to parse
        String validDate = bookingDate;
        return reservationService.getAllReservationsByBookingDate(Instant.parse(validDate));
    }

    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationService.addNewReservation(reservation);
    }

    @PatchMapping("/reservations/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable(name = "id") Integer reservationId, @RequestBody Reservation reservation){
        reservationService.updateReservation(reservationId, reservation);
    }

    @DeleteMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteReservation(@PathVariable(name = "id") Integer reservationId){
        reservationService.deleteReservation(reservationId);
    }

}
