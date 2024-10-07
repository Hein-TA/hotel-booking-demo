package org.example.hotelbookingassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
