package org.example.hotelbookingassignment.dao;

import org.example.hotelbookingassignment.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Integer> {
}
