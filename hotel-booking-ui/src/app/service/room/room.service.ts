import { Injectable, signal } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AvailableRooms, Room } from '../../ds/room';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  BASE_URL = "http://localhost:8080/hotel-booking/backend";
  roomList = signal<AvailableRooms[]>([]);
  room = signal<Room>({id: 0, roomNumber: '', roomType: '', occupation: 0, price: 0.0});

  constructor(private http: HttpClient) { 
  }

  getAvailabeRooms(checkinDate: string, checkoutDate: string): Observable<AvailableRooms[]> {
    return this.http.get<AvailableRooms[]>(this.BASE_URL + "/search-room/" + checkinDate + "/" + checkoutDate);
  }

  getRoomByRoomNumber(checkinDate: string, checkoutDate: string, roomNumber: string): Observable<Room> {
    return this.http.get<Room>(this.BASE_URL + "/search-room/" + checkinDate + "/" + checkoutDate + "/" + roomNumber);
  }
}
