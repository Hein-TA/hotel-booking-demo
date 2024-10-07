package org.example.hotelbookingassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dao.AccountDao;
import org.example.hotelbookingassignment.dao.GuestDao;
import org.example.hotelbookingassignment.dto.AccountDto;
import org.example.hotelbookingassignment.dto.JwtResponse;
import org.example.hotelbookingassignment.dto.LoginDto;
import org.example.hotelbookingassignment.dto.RegisterFormDto;
import org.example.hotelbookingassignment.entity.Account;
import org.example.hotelbookingassignment.entity.Guest;
import org.example.hotelbookingassignment.jwt.JwtAuthenticationProvider;
import org.example.hotelbookingassignment.uitl.EntityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;
    private final GuestService guestService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public Optional<Account> findByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }

    @Transactional
    public AccountDto registerAccount(RegisterFormDto registerFormDto) {
        Guest guest = guestService.registerGuest(EntityUtil.toGuestFromRegisterFormDto(registerFormDto));
        Account account = EntityUtil.toAccount(registerFormDto);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setGuest(guest);
        return EntityUtil.toAccountDto(accountDao.save(account));
    }

    public JwtResponse login(LoginDto loginDto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtAuthenticationProvider.generateToken(auth);

        JwtResponse response = new JwtResponse();
        response.setAccessToken(token);
        return response;
    }
}
