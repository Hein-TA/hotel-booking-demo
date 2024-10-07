package org.example.hotelbookingassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dao.BookingDao;
import org.example.hotelbookingassignment.dao.RoomDao;
import org.example.hotelbookingassignment.dto.AvailableRoomDto;
import org.example.hotelbookingassignment.dto.RoomDto;
import org.example.hotelbookingassignment.entity.Booking;
import org.example.hotelbookingassignment.entity.Room;
import org.example.hotelbookingassignment.exception.AccountNotFoundException;
import org.example.hotelbookingassignment.exception.RoomNotAvailableException;
import org.example.hotelbookingassignment.exception.RoomNotFoundException;
import org.example.hotelbookingassignment.uitl.RoomKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomDao roomDao;
    private final BookingDao bookingDao;

    public RoomDto searchRoomByRoomNumber(String roomNumber, LocalDate checkinDate, LocalDate checkoutDate) {
        Room room = roomDao.findRoomByRoomNumber(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException("There is no room with this number:: " + roomNumber));
        if (bookingDao.existsBookingByRoomNumberAndBookingDate(checkinDate, checkoutDate, roomNumber)) {
            throw new RoomNotAvailableException("RoomNumber " + roomNumber + "is no available for the selected date.");
        }
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getNumber());
        roomDto.setRoomType(room.getRoomType().getType());
        roomDto.setPrice(room.getRoomType().getPrice());
        roomDto.setOccupation(room.getRoomType().getOccupation());
        return roomDto;
    }

    public List<AvailableRoomDto> findAllAvailableRooms(LocalDate checkinDate, LocalDate checkoutDate) {
        Map<RoomKey, AvailableRoomDto> availableRoomMap = new HashMap<>();
        List<Room> rooms = findAllAvailableRoomFromBookingAndRoomList(checkinDate, checkoutDate);
        for (var room : rooms) {
            RoomKey roomKey = new RoomKey(room.getRoomType().getType(), room.getRoomType().getOccupation());
            if (!availableRoomMap.containsKey(roomKey)) {
                AvailableRoomDto availableRoomDto = getAvailableRoomDto(room, rooms);
                availableRoomMap.put(roomKey, availableRoomDto);
            }
        }
        return availableRoomMap.values().stream().sorted(Comparator.comparing(AvailableRoomDto::getPrice)).collect(Collectors.toUnmodifiableList());
    }

    public List<Room> findAllAvailableRoomFromBookingAndRoomList(LocalDate checkinDate, LocalDate checkoutDate) {
        List<Room> roomsFromBooking =  bookingDao.searchAvailableRoomsByDate(checkinDate, checkoutDate).orElse(List.of());

        List<Room> rooms = roomDao.getAvailableRooms().orElse(List.of());
        rooms.addAll(roomsFromBooking);
        return rooms.stream()
                .filter(room -> !bookingDao.existsBookingByRoomNumberAndBookingDate(checkinDate, checkoutDate, room.getNumber()))
                .collect(Collectors.toUnmodifiableList());
    }

    private static AvailableRoomDto getAvailableRoomDto(Room room, List<Room> rooms) {
        AvailableRoomDto availableRoomDto = new AvailableRoomDto();
        availableRoomDto.setType(room.getRoomType().getType());
        availableRoomDto.setOccupation(room.getRoomType().getOccupation());
        availableRoomDto.setPrice(room.getRoomType().getPrice());
        availableRoomDto.setAvailableRoom(rooms.stream()
                .filter(r -> r.getRoomType().getType().equalsIgnoreCase(availableRoomDto.getType()))
                .filter(r -> r.getRoomType().getOccupation() == availableRoomDto.getOccupation())
                .count());
        return availableRoomDto;
    }


//    @Transactional
//    public Room getOneAvailableRoomByType(String type, int occupation) {
//        return findAvailableRoomByType(type, occupation).stream().findAny().get();
//    }
//
//    public List<Room> findAvailableRoomByType(String type, int occupation) {
//        return roomDao.findRoomsByTypeAndOccupation(type, occupation)
//                .stream()
//                .collect(Collectors.toList());
//    }
//
//    public Room findRoomByRoomNumber(String roomNumber) {
//        return roomDao.findRoomByRoomNumber(roomNumber);
//    }

}
