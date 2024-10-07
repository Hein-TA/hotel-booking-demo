package org.example.hotelbookingassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dao.BookingDao;
import org.example.hotelbookingassignment.dto.BookRandomRoomDto;
import org.example.hotelbookingassignment.dto.BookRoomByRoomNumberDto;
import org.example.hotelbookingassignment.dto.BookingHistoryDto;
import org.example.hotelbookingassignment.dto.BookingResult;
import org.example.hotelbookingassignment.service.BookingService;
import org.example.hotelbookingassignment.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/hotel-booking/backend")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final BookingDao bookingDao;

    @PostMapping("/book-random-room")
    public ResponseEntity<BookingResult> bookRandomRoom(@RequestBody BookRandomRoomDto bookingDto) {
        BookingResult bookingResult = bookingService.bookRandomRoom(bookingDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return new ResponseEntity<>(bookingResult, HttpStatus.CREATED);
    }

    @PostMapping("/book-room")
    public ResponseEntity<BookingResult> bookRoom(@RequestBody BookRoomByRoomNumberDto bookRoomByRoomNumberDto) {
        BookingResult bookingResult = bookingService.bookRoomByRoomNumber(bookRoomByRoomNumberDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return new ResponseEntity<>(bookingResult, HttpStatus.CREATED);
    }

    @GetMapping("/get-bookings/{id}")
    public ResponseEntity<List<BookingHistoryDto>> getAllBookingByGuestId(@PathVariable("id") int guestId) {
        return new ResponseEntity<>(bookingService.findBookingsByGuestId(guestId), HttpStatus.OK);
    }

}
