package org.example.hotelbookingassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDto {
    private Integer id;
    private String roomNumber;
    private String roomType;
    private int occupation;
    private double price;
}
