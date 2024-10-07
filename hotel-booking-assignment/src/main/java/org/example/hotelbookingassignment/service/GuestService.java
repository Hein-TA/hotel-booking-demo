package org.example.hotelbookingassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.hotelbookingassignment.dao.AddressDao;
import org.example.hotelbookingassignment.dao.GuestDao;
import org.example.hotelbookingassignment.dto.GuestDto;
import org.example.hotelbookingassignment.entity.Address;
import org.example.hotelbookingassignment.entity.Guest;
import org.example.hotelbookingassignment.exception.AccountNotFoundException;
import org.example.hotelbookingassignment.uitl.EntityUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestDao guestDao;

    public GuestDto findGuestByEmail(String email) {
        return guestDao.findByEmail(email).map(EntityUtil::toGuestDto)
                .orElseThrow(() -> new AccountNotFoundException("There is no guest with email: " + email));
    }

    public Guest registerGuest(Guest guest) {
        Optional<Guest> guestFromDb = guestDao.findByEmail(guest.getEmail());
        if (guestFromDb.isPresent()) {
            guest.setId(guestFromDb.get().getId());
            guest.getAddress().setId(guestFromDb.get().getAddress().getId());
        }
        return guestDao.save(guest);
    }

    public GuestDto updateGuest(GuestDto guestDto) {
        if (!guestDao.existsById(guestDto.getId())) {
            throw new AccountNotFoundException("There is no guest with email: " + guestDto.getEmail());
        }
        return EntityUtil.toGuestDto(guestDao.save(EntityUtil.toGuest(guestDto)));
    }

}
