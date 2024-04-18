package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer flightId;
    @Enumerated(EnumType.STRING)
    private Airline airline;
    @Enumerated(EnumType.STRING)
    private Airport departureAirport;
    @Enumerated(EnumType.STRING)
    private Airport arrivalAirport;
    private Instant departureTime;
    private Instant arrivalTime;
    private double price;
    //private List<Flight> layover;

    //public Flight(Airline airline, Airport departureAirport, Airport arrivalAirport, Instant departureTime, Instant arrivalTime, double price, List<Flight> layover) {
    //    this.airline = airline;
    //    this.departureAirport = departureAirport;
    //    this.arrivalAirport = arrivalAirport;
    //    this.departureTime = departureTime;
    //    this.arrivalTime = arrivalTime;
    //    this.price = price;
    //    this.layover = layover;
    //}

    public Flight(Airline airline, Airport departureAirport, Airport arrivalAirport, Instant departureTime, Instant arrivalTime, double price) {
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }
}
