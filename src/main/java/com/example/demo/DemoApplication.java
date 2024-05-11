package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.services.implementations.FlightService;
import com.example.demo.services.implementations.ReservationService;
import com.example.demo.services.implementations.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
@Slf4j
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Transactional
	@Bean
	CommandLineRunner run(UserService userService, FlightService flightService, ReservationService reservationService) {
		return args -> {
			userService.addNewRole(new Role( "ROLE_USER"));
			userService.addNewRole(new Role("ROLE_ADMIN"));

			userService.addNewUser(new User( "John_Doe",  "1234", new ArrayList<>()));
			userService.addNewUser(new User( "James_Smith",  "1234", new ArrayList<>()));
			userService.addNewUser(new User( "Jane_Carry",  "1234", new ArrayList<>()));
			userService.addNewUser(new User( "Chris_Anderson",  "1234", new ArrayList<>()));

			userService.addRoleToUser("John_Doe", "ROLE_USER");
			userService.addRoleToUser("James_Smith", "ROLE_ADMIN");
			userService.addRoleToUser("Jane_Carry", "ROLE_USER");
			userService.addRoleToUser("Chris_Anderson", "ROLE_ADMIN");
			userService.addRoleToUser("Chris_Anderson", "ROLE_USER");

			flightService.addNewFlight(new Flight(Airline.AirCanada, Airport.ATL, Airport.JFK, Instant.parse("2024-07-10T10:30:00Z"), Instant.parse("2024-07-10T16:45:00Z"),80));

			Flight savedFlight = flightService.getFlightById(1);
			User savedUser = userService.getUserById(1);

			if(savedFlight != null && savedUser != null) {
				reservationService.addNewReservation(new Reservation(savedFlight, savedUser, 2, savedFlight.getPrice() * 2, Instant.now()));
			}else{
				log.error("User or Flight not found");
			}

			};
	}
}
