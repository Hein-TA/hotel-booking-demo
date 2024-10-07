package org.example.hotelbookingassignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @EmbeddedId
    private BookingId id;
    private String bookingNumber;
    private int extra;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    @MapsId("roomId")
    private Room room;

    public void setRoom(Room room) {
        room.setAvailable(false);
        this.room = room;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", guest=" + guest.getId() +
                ", room=" + room.getNumber() +
                ", isRoomAvailable=" + room.isAvailable() +
                '}';
    }
}
