package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PassengerTest {

    static User userTest;
    static User userTestTwo;
    static Passenger passengerTest;
    static Passenger passengerTestTwo;

    @BeforeEach
    void setUp(){
        userTest = new User("dummyuser","strongPassword123!");
        userTestTwo = new User("dummyuser", "weak1234");
        passengerTest = new Passenger(userTest,"Carolina", "Gonzalez","YB43876");
        passengerTestTwo = new Passenger(userTestTwo,"Lucas","Fridman","AAA30304");
    }

    @Test
    public void createEmptyPassenger(){
        Passenger emptyPassenger = new Passenger();
        assertNotNull(emptyPassenger);
        assertEquals(null, emptyPassenger.getName());
    }

    @Test
    public void checkPassengerIsCorrect(){
        assertEquals("Carolina", passengerTest.getName());
        assertEquals("Lucas", passengerTestTwo.getName());
    }

    @Test
    public void userIdSetterTest(){
        passengerTest.setUserId(userTestTwo);
        assertEquals(userTestTwo,passengerTestTwo.getUserId());
    }

    @Test
    public void getterTest(){
        assertEquals("Carolina", passengerTest.getName());
        assertEquals("Gonzalez", passengerTest.getSurname());
        assertEquals("YB43876", passengerTest.getPassport());
        assertEquals("dummyuser",passengerTest.getUserId().getUsername());
    }
}
