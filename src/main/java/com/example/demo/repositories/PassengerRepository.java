package com.example.demo.repositories;

import com.example.demo.models.Passenger;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
    Optional<List<Passenger>> findAllByUserId(User userId);

}
