import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Guest } from '../../ds/guest';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GuestService {
  BASE_URL = "http://localhost:8080/hotel-booking/backend/guest";
  
  dummyGuest = {
    id: 0,
    firstName: '',
    lastName: '',
    birthDate: '',
    email: '',
    phone: '',
    address: {
      id: 0,
      street: '',
      city: '',
      country: ''
    }
  }
  guest = signal<Guest>(this.dummyGuest);

  constructor(private http: HttpClient) { }

  findGuestByEmail(email: any): Observable<Guest> {
    return this.http.get<Guest>(this.BASE_URL + "/find-by-email/" + email);
  }

updateGuestInfo(guest: Guest): Observable<Guest> {
  const httpHeaders = new HttpHeaders();
  httpHeaders.set("Content-Type", "application/json");
  return this.http.post<Guest>(this.BASE_URL + "/update-guest", guest, {headers: httpHeaders});
}

}
