import { Address, Guest } from "./guest"
import { Room } from "./room"

export type BookRandomRoom = {
    checkinDate: string,
    checkoutDate: string,
    type: string,
    occupation: number,
    guest: Guest,
    extra: number
}

export type BookByRoom = {
    checkinDate: string,
    checkoutDate: string,
    guest: Guest,
    room: Room,
    extra: number
}

export type BookHistory = {
    checkinDate: string,
    checkoutDate: string,
    roomNumber: string,
    roomType: string,
    occupation: number,
    price: number,
    extra: number
}
