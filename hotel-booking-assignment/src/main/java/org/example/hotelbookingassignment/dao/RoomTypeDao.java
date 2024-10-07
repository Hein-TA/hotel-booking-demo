package org.example.hotelbookingassignment.dao;

import org.example.hotelbookingassignment.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeDao extends JpaRepository<RoomType, Integer> {
}
