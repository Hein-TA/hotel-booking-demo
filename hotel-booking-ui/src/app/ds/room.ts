export interface Room {
    id: number,
    roomNumber: string,
    roomType: string,
    occupation: number,
    price: number
}

export type AvailableRooms = {
    type: string,
    occupation: number,
    price: number,
    availableRoom: number
}

