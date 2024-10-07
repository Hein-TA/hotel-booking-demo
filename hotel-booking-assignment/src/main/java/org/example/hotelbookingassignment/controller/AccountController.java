package org.example.hotelbookingassignment.controller;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dto.*;
import org.example.hotelbookingassignment.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("*")
@RestController
@RequestMapping("/hotel-booking/backend/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AccountDto> registerGuest(@RequestBody RegisterFormDto registerFormDto) {
        if (accountService.findByEmail(registerFormDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        return new ResponseEntity<>(accountService.registerAccount(registerFormDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(accountService.login(loginDto), HttpStatus.OK);
    }
}
