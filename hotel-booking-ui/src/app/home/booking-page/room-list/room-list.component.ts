import { Component, inject, OnInit, signal } from '@angular/core'
import { CommonModule } from '@angular/common';
import { AvailableRooms } from '../../../ds/room';
import { Observable } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { RoomComponent } from './room/room.component';
import { BookingService } from '../../../service/booking/booking.service';
import { DateService } from '../../../service/date/date.service';
import { RoomService } from '../../../service/room/room.service';
import { RoomDetailsComponent } from "../register-room/room-details/room-details.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-room-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, RoomComponent, RoomDetailsComponent],
  templateUrl: './room-list.component.html',
  styleUrl: './room-list.component.css'
})
export class RoomListComponent implements OnInit {
  dateService = inject(DateService);
  roomService = inject(RoomService);

  showDetails = false;
  room!: AvailableRooms;
  roomList = this.roomService.roomList;

  checkinDate = this.dateService.checkinDate;
  checkoutDate = this.dateService.checkoutDate;

  checkinMinDate = this.dateService.checkinMinDate;
  checkoutMinDate = this.dateService.checkoutMinDate;

  ngOnInit(): void {
    this.searchAvailableRoom();
  }

  searchAvailableRoom(): void {
    this.roomService.getAvailabeRooms(this.checkinDate(), this.checkoutDate())
    .subscribe({
      next: data => this.roomService.roomList.set(data),
      error: err => console.log(err)
    });
  }

}
