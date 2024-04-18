package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer passengerId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
    private String name;
    private String surname;
    private String passport;

    public Passenger(User userId, String name, String surname, String passport) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }
}
