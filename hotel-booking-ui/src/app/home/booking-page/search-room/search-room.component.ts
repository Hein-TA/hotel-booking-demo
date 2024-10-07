import { Component, inject, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { BookingService } from '../../../service/booking/booking.service';
import { DateService } from '../../../service/date/date.service';
import { RoomService } from '../../../service/room/room.service';
import { AuthService } from '../../../service/auth/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-room',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './search-room.component.html',
  styleUrl: './search-room.component.css'
})
export class SearchRoomComponent implements OnInit {
  dateService = inject(DateService);
  roomService = inject(RoomService);
  authService = inject(AuthService);
  router: Router = inject(Router);

  isUserLoggedin = false;
  dropdownOpen = false;
  roomNumber!: string;
  checkinDate = this.dateService.checkinDate;
  checkoutDate = this.dateService.checkoutDate;

  checkinMinDate = this.dateService.checkinMinDate;
  checkoutMinDate = this.dateService.checkoutMinDate;

  constructor() {
    if (!this.checkinDate() && !this.checkoutDate()) {
      this.checkinDate.set(this.dateService.checkinMinDate());
      this.checkoutDate.set(this.dateService.checkoutMinDate());
    }
  }

  ngOnInit(): void {
    if (this.authService.isUserLogin()) {
      this.isUserLoggedin = !this.isUserLoggedin;
    }
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  searchRoom() {
    this.dateService.checkinDate.set(this.checkinDate());
    this.dateService.checkoutDate.set(this.checkoutDate());

    if (this.isUserLoggedin && this.roomNumber) {
      this.roomService.getRoomByRoomNumber(this.checkinDate(), this.checkoutDate(), this.roomNumber)
      .subscribe({
        next: data => this.roomService.room.set(data),
        error: err => console.log(err),
        complete: () => this.router.navigate(["booking/register-room"])
      });
    }
    else {
      this.roomService.getAvailabeRooms(this.checkinDate(), this.checkoutDate())
      .subscribe({
        next: data => this.roomService.roomList.set(data),
        error: err => console.log(err),
        complete: () => this.router.navigate(["booking/room-list"])
      });
    }
  }
}
