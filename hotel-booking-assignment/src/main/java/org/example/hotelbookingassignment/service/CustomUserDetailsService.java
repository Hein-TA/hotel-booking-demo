package org.example.hotelbookingassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dao.AccountDao;
import org.example.hotelbookingassignment.exception.AccountNotFoundException;
import org.example.hotelbookingassignment.uitl.SecurityUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountDao.findAccountByEmail(username)
                .map(SecurityUserDetails::new)
                .orElseThrow(() -> new AccountNotFoundException("Email not found with email: " + username));
    }
}
