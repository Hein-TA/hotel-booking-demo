package org.example.hotelbookingassignment.dao;

import org.example.hotelbookingassignment.entity.Booking;
import org.example.hotelbookingassignment.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingDao extends JpaRepository<Booking, Integer> {

    @Query("""
    select b from Booking b where b.guest.id = :id
    """)
    List<Booking> findBookingsByGuestId(@Param("id") int id);

    @Query("""
    select b.room from Booking b where not (cast(:checkinDate as date) < b.id.checkOutDate and cast(:checkoutDate as date) > b.id.checkInDate)
    """)
    Optional<List<Room>> searchAvailableRoomsByDate(@Param("checkinDate") LocalDate checkinDate, @Param("checkoutDate") LocalDate checkoutDate);

    @Query("""
    select exists (select b from Booking b where :checkinDate < b.id.checkOutDate and :checkoutDate > b.id.checkInDate and b.room.number = :roomNumber)
    """)
    Boolean existsBookingByRoomNumberAndBookingDate(@Param("checkinDate") LocalDate checkinDate, @Param("checkoutDate") LocalDate checkoutDate, @Param("roomNumber") String roomNumber);
}
