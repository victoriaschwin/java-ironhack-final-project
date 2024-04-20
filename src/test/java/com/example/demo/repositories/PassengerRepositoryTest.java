package com.example.demo.repositories;

import com.example.demo.models.Passenger;
import com.example.demo.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PassengerRepositoryTest {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private UserRepository userRepository;

    private List<Passenger> passengers;
    private List<User> users;

    @BeforeEach
    void setUp(){
        users = new ArrayList<>();
        passengers = new ArrayList<>();

        users.add(new User("testUser", "password12"));
        users.add(new User("testUserTwo", "nopassword"));
        passengers.add(new Passenger(users.get(0),"Clara","Lopez","Y567893"));
        passengers.add(new Passenger(users.get(0), "Leandro", "Bell", "AAA309765"));
        passengers.add(new Passenger(users.get(1),"Jordi","Blaquer", "LG765432"));

        userRepository.saveAll(users);
        passengerRepository.saveAll(passengers);
    }

    @AfterEach
    void tearUp(){
        passengerRepository.deleteAll(passengers);
        userRepository.deleteAll(users);
    }
    @Test
    void findAllByUserIdShouldReturnTwo(){
        Optional<List<Passenger>> maybePassengers = passengerRepository.findAllByUserId(users.get(0));

        assertTrue(maybePassengers.isPresent());
        assertEquals(users.get(0), maybePassengers.get().get(0));
        assertEquals(users.get(1), maybePassengers.get().get(1));
    }
    @Test
    void findByUserIdShouldReturnZero(){
        User testUser = new User("noUser", "1234");
        userRepository.save(testUser);

        Optional<List<Passenger>> noPassengers = passengerRepository.findAllByUserId(testUser);
        assertEquals(0,noPassengers.get().size());
    }

}
