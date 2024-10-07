package org.example.hotelbookingassignment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;
    private boolean isAvailable = true;

    @ManyToOne(cascade = CascadeType.ALL)
    private RoomType roomType;

    public Room(Integer id, String number, boolean isAvailable) {
        this.id = id;
        this.number = number;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
