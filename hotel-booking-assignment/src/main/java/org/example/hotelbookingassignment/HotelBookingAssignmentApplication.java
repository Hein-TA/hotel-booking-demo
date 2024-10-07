package org.example.hotelbookingassignment;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dao.*;
import org.example.hotelbookingassignment.entity.*;
import org.example.hotelbookingassignment.service.BookingService;
import org.example.hotelbookingassignment.service.RoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@SpringBootApplication
@RequiredArgsConstructor
public class HotelBookingAssignmentApplication {
    private final RoomDao roomDao;
    private final RoomTypeDao roomTypeDao;
    private final RoomService roomService;
    private final AccountDao accountDao;

    @Bean @Profile("dev")
    @Transactional
    public ApplicationRunner runner() {
        return args -> {
            addRooms();
            addAdmin();
        };
    }

    public void addAdmin() {
        Account admin = new Account();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345");
        admin.setRole("ROLE_ADMIN");
        accountDao.save(admin);
    }

    /*
    Standard room for 1st floor - 5 rooms - f
    Standard room for 2nd floor - 4 rooms - f
    Superior room for 3rd floor - 4 rooms - f
    Superior room for 4th floor - 3 rooms - f
    Deluxe room for 5th floor - 3 rooms - f
    Deluxe room for 6th floor - 3 rooms - f
    Suite room for 7th floor - 2 rooms - f
    Suite room for 8th floor - 2 rooms - f
     */
    public void addRooms() {
        RoomType roomType1 = new RoomType(null, "Standard", 2, 200);
        RoomType roomType2 = new RoomType(null, "Standard", 4, 280);
        RoomType roomType3 = new RoomType(null, "Superior", 2, 320);
        RoomType roomType4 = new RoomType(null, "Superior", 4, 400);
        RoomType roomType5 = new RoomType(null, "Deluxe", 2, 480);
        RoomType roomType6 = new RoomType(null, "Deluxe", 4, 620);
        RoomType roomType7 = new RoomType(null, "Suite", 2, 730);
        RoomType roomType8 = new RoomType(null, "Suite", 4, 880);

        Room room1 = new Room(null, "101", true);
        Room room2 = new Room(null, "102", true);
        Room room3 = new Room(null, "103", true);
        Room room4 = new Room(null, "104", true);
        Room room5 = new Room(null, "105", true);
        Room room6 = new Room(null, "201", true);
        Room room7 = new Room(null,"202", true);
        Room room8 = new Room(null, "203", true);
        Room room9 = new Room(null, "204", true);
        Room room10 = new Room(null, "301", true);
        Room room11 = new Room(null, "302", true);
        Room room12 = new Room(null, "303", true);
        Room room13 = new Room(null, "304", true);
        Room room14 = new Room(null, "401", true);
        Room room15 = new Room(null, "402", true);
        Room room16 = new Room(null, "403", true);
        Room room17 = new Room(null, "501", true);
        Room room18 = new Room(null, "502", true);
        Room room19 = new Room(null, "503", true);
        Room room20 = new Room(null, "601", true);
        Room room21 = new Room(null, "602", true);
        Room room22 = new Room(null, "603", true);
        Room room23 = new Room(null, "701", true);
        Room room24 = new Room(null, "702", true);
        Room room25 = new Room(null, "801", true);
        Room room26 = new Room(null, "802", true);

        roomType1.addRoom(room1);
        roomType1.addRoom(room2);
        roomType1.addRoom(room3);
        roomType1.addRoom(room4);
        roomType1.addRoom(room5);

        roomType2.addRoom(room6);
        roomType2.addRoom(room7);
        roomType2.addRoom(room8);
        roomType2.addRoom(room9);

        roomType3.addRoom(room10);
        roomType3.addRoom(room11);
        roomType3.addRoom(room12);
        roomType3.addRoom(room13);

        roomType4.addRoom(room14);
        roomType4.addRoom(room15);
        roomType4.addRoom(room16);

        roomType5.addRoom(room17);
        roomType5.addRoom(room18);
        roomType5.addRoom(room19);

        roomType6.addRoom(room20);
        roomType6.addRoom(room21);
        roomType6.addRoom(room22);

        roomType7.addRoom(room23);
        roomType7.addRoom(room24);

        roomType8.addRoom(room25);
        roomType8.addRoom(room26);


        roomTypeDao.save(roomType1);
        roomTypeDao.save(roomType2);
        roomTypeDao.save(roomType3);
        roomTypeDao.save(roomType4);
        roomTypeDao.save(roomType5);
        roomTypeDao.save(roomType6);
        roomTypeDao.save(roomType7);
        roomTypeDao.save(roomType8);
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingAssignmentApplication.class, args);
    }

}
