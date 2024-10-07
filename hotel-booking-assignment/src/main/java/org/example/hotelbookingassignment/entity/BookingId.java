package org.example.hotelbookingassignment.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class BookingId {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer roomId;

    @Override
    public String toString() {
        return "BookingId{" +
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
