package org.example.hotelbookingassignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private int occupation;
    private double price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomType")
    private List<Room> rooms = new ArrayList<>();

    public RoomType(Integer id, String type, int occupation, double price) {
        this.id = id;
        this.type = type;
        this.occupation = occupation;
        this.price = price;
    }

    public void addRoom(Room room) {
        room.setRoomType(this);
        rooms.add(room);
    }

}
