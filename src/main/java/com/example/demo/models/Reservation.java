package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer reservationId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="flight_id")
    private Flight flightId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User userId;
    private Integer numberPassengers;
    private double pricePaid;
    private Instant bookingDate;

    public Reservation(Flight flightId, User userId, Integer numberPassengers, double pricePaid, Instant bookingDate) {
        this.flightId = flightId;
        this.userId = userId;
        this.numberPassengers = numberPassengers;
        this.pricePaid = pricePaid;
        this.bookingDate = bookingDate;
    }
}
