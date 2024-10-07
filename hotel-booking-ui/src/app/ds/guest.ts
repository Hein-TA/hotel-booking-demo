export type Guest = {
    id: number,
    firstName: string;
    lastName: string,
    birthDate: string,
    email: string,
    phone: string,
    address: Address
}

export type Address = {
    id: number,
    street: string,
    city: string,
    country: string
}