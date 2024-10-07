package org.example.hotelbookingassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RoomNotAvailableException extends ResponseStatusException {
    public RoomNotAvailableException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
