package org.example.hotelbookingassignment.uitl;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.hotelbookingassignment.dto.BookRoomByRoomNumberDto;
import org.example.hotelbookingassignment.dto.BookingHistoryDto;
import org.example.hotelbookingassignment.dto.BookingResult;
import org.example.hotelbookingassignment.entity.Booking;
import org.example.hotelbookingassignment.entity.BookingId;

public class BookingEntityUtil {

    public static Booking toBooking(BookRoomByRoomNumberDto bookRoomByRoomNumberDto) {
        BookingId bookingId = new BookingId();
        bookingId.setCheckInDate(bookRoomByRoomNumberDto.getCheckinDate());
        bookingId.setCheckOutDate(bookRoomByRoomNumberDto.getCheckoutDate());

        Booking booking = new Booking();
        booking.setBookingNumber(generateBookingNumber());
        booking.setId(bookingId);
        booking.setGuest(EntityUtil.toGuest(bookRoomByRoomNumberDto.getGuest()));
        booking.setRoom(EntityUtil.toRoom(bookRoomByRoomNumberDto.getRoom()));
        booking.setExtra(bookRoomByRoomNumberDto.getExtra());
        return booking;
    }

    public static String generateBookingNumber() {
        return RandomStringUtils.randomNumeric(7);
    }

    public static BookingHistoryDto toBookingHistoryDto(Booking booking) {
        BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
        bookingHistoryDto.setCheckinDate(booking.getId().getCheckInDate());
        bookingHistoryDto.setCheckoutDate(booking.getId().getCheckOutDate());
        bookingHistoryDto.setRoomNumber(booking.getRoom().getNumber());
        bookingHistoryDto.setRoomType(booking.getRoom().getRoomType().getType());
        bookingHistoryDto.setOccupation(booking.getRoom().getRoomType().getOccupation());
        bookingHistoryDto.setPrice(booking.getRoom().getRoomType().getPrice());
        bookingHistoryDto.setExtra(booking.getExtra());
        return bookingHistoryDto;
    }

    public static BookingResult toBookingResult(Booking booking) {
        BookingResult bookingResult = new BookingResult();
        bookingResult.setBookingNumber(booking.getBookingNumber());
        bookingResult.setMessage("Room Successfully booked!");
        return bookingResult;
    }
}
