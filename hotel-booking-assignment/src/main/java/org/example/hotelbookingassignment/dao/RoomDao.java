package org.example.hotelbookingassignment.dao;

import org.example.hotelbookingassignment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomDao extends JpaRepository<Room, Integer> {

    @Query("""
    select r from Room r where r.isAvailable = true
    """)
    Optional<List<Room>> getAvailableRooms();

    @Query("""
    select r from Room r where r.roomType.type = :roomType and r.roomType.occupation = :occupation and r.isAvailable = true
    """)
    List<Room> findRoomsByTypeAndOccupation(@Param("roomType") String type, @Param("occupation") int occupation);

    @Query("""
    select r from Room r where r.number = :roomNumber
    """)
    Optional<Room> findRoomByRoomNumber(@Param("roomNumber") String roomNumber);

}
