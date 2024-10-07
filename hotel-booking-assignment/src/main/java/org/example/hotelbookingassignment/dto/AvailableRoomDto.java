package org.example.hotelbookingassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableRoomDto {
    private String type;
    private int occupation;
    private double price;
    private long availableRoom;
}
