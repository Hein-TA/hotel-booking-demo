package org.example.hotelbookingassignment.uitl;

import org.example.hotelbookingassignment.dto.*;
import org.example.hotelbookingassignment.entity.*;
import org.springframework.beans.BeanUtils;

public class EntityUtil {

    public static Account toAccount(RegisterFormDto registerFormDto) {
        Account account = new Account();
        account.setEmail(registerFormDto.getEmail());
        account.setPassword(registerFormDto.getPassword());
        account.setRole(registerFormDto.getRole());
        return account;
    }

    public static AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setEmail(account.getEmail());
        accountDto.setPassword(account.getPassword());
        accountDto.setRole(account.getRole());
        accountDto.setGuestId(accountDto.getGuestId());
        return accountDto;
    }

    public static Guest toGuest(GuestDto guestDto) {
        Guest guest = new Guest();
        guest.setAddress(toAddress(guestDto.getAddress()));
        BeanUtils.copyProperties(guestDto, guest);
        return guest;
    }

    public static GuestDto toGuestDto(Guest guest) {
        GuestDto guestDto = new GuestDto();
        guestDto.setAddress(toAddressDto(guest.getAddress()));
        BeanUtils.copyProperties(guest, guestDto);
        return guestDto;
    }

    public static Guest toGuestFromRegisterFormDto(RegisterFormDto registerFormDto) {
        Address address = new Address();
        address.setStreet(registerFormDto.getStreet());
        address.setCity(registerFormDto.getCity());
        address.setCountry(registerFormDto.getCountry());

        Guest guest = new Guest();
        guest.setFirstName(registerFormDto.getFirstName());
        guest.setLastName(registerFormDto.getLastName());
        guest.setBirthDate(registerFormDto.getBirthDate());
        guest.setEmail(registerFormDto.getEmail());
        guest.setPhone(registerFormDto.getPhone());
        guest.setAddress(address);

        return guest;
    }

    public static Address toAddress(AddressDto addressDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return address;
    }

    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);
        return addressDto;
    }

    public static Room toRoom(RoomDto roomDto) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDto, room);
        return room;
    }

}
