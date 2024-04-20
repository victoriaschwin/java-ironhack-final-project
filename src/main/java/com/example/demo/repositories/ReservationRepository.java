package com.example.demo.repositories;

import com.example.demo.models.Reservation;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
Optional<Reservation> findById(Integer reservationId);
Optional<List<Reservation>> findAllByUserId(User userId);
Optional<List<Reservation>> findByBookingDate(Instant bookingDate);

}
