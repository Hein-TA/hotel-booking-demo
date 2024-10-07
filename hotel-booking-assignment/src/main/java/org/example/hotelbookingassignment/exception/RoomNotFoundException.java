package org.example.hotelbookingassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class RoomNotFoundException extends ResponseStatusException {
    public RoomNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
