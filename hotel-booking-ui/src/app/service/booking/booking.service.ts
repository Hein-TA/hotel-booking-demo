import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { BookByRoom, BookHistory, BookRandomRoom } from '../../ds/booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  BASE_URL = "http://localhost:8080/hotel-booking/backend";

  constructor(private http: HttpClient) { 
    
   }

  bookRandomRoom(booking: BookRandomRoom): Observable<string> {
    return this.http.post<string>(this.BASE_URL + "/book-random-room", booking);
  }

  bookRoom(booking: BookByRoom): Observable<string> {
    return this.http.post<string>(this.BASE_URL + "/book-room", booking);
  }

  getBookingsByGuestId(id: number): Observable<BookHistory[]> {
    return this.http.get<BookHistory[]>(this.BASE_URL + "/get-bookings/" + id);
  }
}
