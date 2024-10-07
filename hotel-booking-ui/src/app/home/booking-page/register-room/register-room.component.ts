import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterModule } from '@angular/router';
import { BookByRoom, BookRandomRoom } from '../../../ds/booking';
import { FormsModule } from '@angular/forms';
import { BookingService } from '../../../service/booking/booking.service';
import { GuestService } from '../../../service/guest/guest.service';
import { DateService } from '../../../service/date/date.service';
import { RoomService } from '../../../service/room/room.service';
import { AuthService } from '../../../service/auth/auth.service';
import { CommonModule } from '@angular/common';
import { RoomDetailsComponent } from "./room-details/room-details.component";

@Component({
  selector: 'app-register-room',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule, RoomDetailsComponent],
  templateUrl: './register-room.component.html',
  styleUrl: './register-room.component.css'
})
export class RegisterRoomComponent implements OnInit {
  guestService = inject(GuestService);
  bookingService = inject(BookingService);
  dateService = inject(DateService);
  roomService = inject(RoomService);
  authService = inject(AuthService);
  route: ActivatedRoute = inject(ActivatedRoute);
  navigator: Router = inject(Router);

  bookRandom!: BookRandomRoom;
  bookRoom!: BookByRoom;
  guest = this.guestService.guest;
  room = this.roomService.room;
  checkinDate!: string;
  checkoutDate!: string;
  roomPriceWithoutExtraBed!: number;
  dayCount: any;
  totalPrice = 0;
  extraBedCount = 0;
  showDetails = false;


  ngOnInit(): void {
    this.navigator.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        window.scroll(0, 0);
      }
    })
    this.checkinDate = this.dateService.checkinDate();
    this.checkoutDate = this.dateService.checkoutDate()
    ;
    const date1 = new Date(this.checkinDate);
    const date2 = new Date(this.checkoutDate);
    const days = date2.getTime() - date1.getTime();
    this.dayCount = days / (1000 * 3600 * 24);

    this.roomPriceWithoutExtraBed = this.room().price * this.dayCount;
    this.totalPrice = this.roomPriceWithoutExtraBed;
  }

  changePrice() {
    if (this.extraBedCount == 0 && this.totalPrice > this.roomPriceWithoutExtraBed) {
      this.totalPrice -= (this.room().price * 0.3) * this.dayCount;
    }
    if (this.extraBedCount == 1) {
      this.totalPrice += (this.room().price * 0.3) * this.dayCount;
    }
  }

  showDetailsEvent() {
    this.showDetails = !this.showDetails;
    document.body.style.overflow = 'hidden';
  }

  closeDetailsEvent() {
    this.showDetails = false;
    document.body.style.overflow = 'auto';
  }

  regiesterGuestAndBookRoom(): void {
    if (this.authService.isUserLogin() && this.roomService.room().id) {
      this.bookRoom = {checkinDate: this.checkinDate, checkoutDate: this.checkoutDate, guest: this.guest(), room: this.roomService.room(), extra: this.extraBedCount};
      this.bookingService.bookRoom(this.bookRoom).subscribe({
        next: data => console.log(data),
        error: err => console.log(err),
        complete: () => this.navigator.navigate(["/"])
      });
    }
    else {
      this.bookRandom = {checkinDate: this.checkinDate, checkoutDate: this.checkoutDate, type: this.roomService.room().roomType, occupation: this.roomService.room().occupation, guest: this.guest(), extra: this.extraBedCount}
      this.bookingService.bookRandomRoom(this.bookRandom).subscribe({
        next: data => console.log(data),
        error: err => console.log(err),
        complete: () => this.navigator.navigate(["/"])
      });
    }
    
  }
}
