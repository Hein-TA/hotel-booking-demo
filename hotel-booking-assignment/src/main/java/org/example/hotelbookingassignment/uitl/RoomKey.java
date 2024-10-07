package org.example.hotelbookingassignment.uitl;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class RoomKey {
    private String type;
    private int occupation;

    public RoomKey(String type, int occupation) {
        this.type = type;
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomKey roomKey = (RoomKey) o;
        return occupation == roomKey.occupation && type.equalsIgnoreCase(roomKey.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type.toLowerCase(), occupation);
    }
}

