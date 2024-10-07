package org.example.hotelbookingassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dto.GuestDto;
import org.example.hotelbookingassignment.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/hotel-booking/backend/guest")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<GuestDto> findGuestByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(guestService.findGuestByEmail(email));
    }

    @PostMapping("/update-guest")
    public ResponseEntity<GuestDto> updateGuest(@RequestBody GuestDto guestDto) {
        return new ResponseEntity<>(guestService.updateGuest(guestDto), HttpStatus.CREATED);
    }
}
