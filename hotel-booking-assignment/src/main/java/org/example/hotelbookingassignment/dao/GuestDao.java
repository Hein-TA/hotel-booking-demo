package org.example.hotelbookingassignment.dao;

import org.example.hotelbookingassignment.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GuestDao extends JpaRepository<Guest, Integer> {

    Optional<Guest> findByEmail(String email);

}
