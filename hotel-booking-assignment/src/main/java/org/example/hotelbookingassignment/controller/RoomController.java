package org.example.hotelbookingassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dto.AvailableRoomDto;
import org.example.hotelbookingassignment.dto.RoomDto;
import org.example.hotelbookingassignment.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/hotel-booking/backend")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/search-room/{checkin}/{checkout}/{roomNumber}")
    public ResponseEntity<RoomDto> getAvailableRoomByRoomNumber(@PathVariable("checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkinDate,
                                                                @PathVariable("checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkoutDate,
                                                                @PathVariable("roomNumber") String roomNumber) {
        return new ResponseEntity<>(roomService.searchRoomByRoomNumber(roomNumber, checkinDate, checkoutDate), HttpStatus.OK);
    }

    @GetMapping("/search-room/{checkin}/{checkout}")
    public ResponseEntity<List<AvailableRoomDto>> getAvailableRoom(@PathVariable("checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkinDate,
                                                                   @PathVariable("checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkoutDate) {
        return ResponseEntity.ok(roomService.findAllAvailableRooms(checkinDate, checkoutDate));
    }
}
