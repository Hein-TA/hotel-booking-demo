package org.example.hotelbookingassignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hotelbookingassignment.entity.Guest;
import org.example.hotelbookingassignment.entity.Room;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class BookRoomByRoomNumberDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkinDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkoutDate;

    private GuestDto guest;
    private RoomDto room;
    private int extra;
}
