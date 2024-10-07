package org.example.hotelbookingassignment.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.hotelbookingassignment.entity.Guest;

@Getter
@Setter
public class AccountDto {
    private Integer id;
    private String email;
    private String password;
    private String role;
    private int guestId;
}
