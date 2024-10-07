package org.example.hotelbookingassignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterFormDto {
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String email;
    private String password;
    private String phone;
    private String street;
    private String city;
    private String country;
    private String role;

    RegisterFormDto() {
        role = "ROLE_GUEST";
    }
}
