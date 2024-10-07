package org.example.hotelbookingassignment.service;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.hotelbookingassignment.dao.BookingDao;
import org.example.hotelbookingassignment.dto.BookRandomRoomDto;
import org.example.hotelbookingassignment.dto.BookRoomByRoomNumberDto;
import org.example.hotelbookingassignment.dto.BookingHistoryDto;
import org.example.hotelbookingassignment.dto.BookingResult;
import org.example.hotelbookingassignment.entity.*;
import org.example.hotelbookingassignment.uitl.BookingEntityUtil;
import org.example.hotelbookingassignment.uitl.EntityUtil;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingDao bookingDao;
    private final RoomService roomService;
    private final GuestService guestService;

    public List<BookingHistoryDto> findBookingsByGuestId(int id) {
        return bookingDao.findBookingsByGuestId(id)
                .stream()
                .map(BookingEntityUtil::toBookingHistoryDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<BookingResult> bookRoomByRoomNumber(BookRoomByRoomNumberDto bookRoomByRoomNumberDto) {
        Booking booking = BookingEntityUtil.toBooking(bookRoomByRoomNumberDto);
        return Optional.of(BookingEntityUtil.toBookingResult(bookingDao.save(booking)));
    }

    public Optional<BookingResult> bookRandomRoom(BookRandomRoomDto bookingRandomDto) {
        Booking booking = getBookingFromBookingDto(bookingRandomDto);
        booking.setBookingNumber(BookingEntityUtil.generateBookingNumber());
        booking.setExtra(bookingRandomDto.getExtra());
        return Optional.of(BookingEntityUtil.toBookingResult(bookingDao.save(booking)));
    }

    private Booking getBookingFromBookingDto(BookRandomRoomDto bookRandomRoomDto) {
        Guest guest = EntityUtil.toGuest(bookRandomRoomDto.getGuest());

        BookingId bookingId = new BookingId();
        bookingId.setCheckInDate(bookRandomRoomDto.getCheckinDate());
        bookingId.setCheckOutDate(bookRandomRoomDto.getCheckoutDate());

        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setGuest(guestService.registerGuest(guest));
        booking.setRoom(getAvailableRoom(bookRandomRoomDto));
        booking.setExtra(bookRandomRoomDto.getExtra());
        return booking;
    }

    private Room getAvailableRoom(BookRandomRoomDto bookingDto) {
        return roomService.findAllAvailableRoomFromBookingAndRoomList(bookingDto.getCheckinDate(), bookingDto.getCheckoutDate())
                .stream()
                .filter(room -> room.getRoomType().getType().equalsIgnoreCase(bookingDto.getType()))
                .filter(room -> room.getRoomType().getOccupation() == bookingDto.getOccupation())
                .findAny().get();
    }
}
