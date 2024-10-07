import { Component, inject } from '@angular/core';
import { BookingService } from '../../../service/booking/booking.service';
import { GuestService } from '../../../service/guest/guest.service';
import { BookHistory } from '../../../ds/booking';
import { HistoryCardComponent } from "./history-card/history-card.component";
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-booking-history',
  standalone: true,
  imports: [HistoryCardComponent, CommonModule, RouterModule],
  templateUrl: './booking-history.component.html',
  styleUrl: './booking-history.component.css'
})
export class BookingHistoryComponent {
  bookingService = inject(BookingService);
  guestService = inject(GuestService);

  id = this.guestService.guest().id;
  bookHistory!: BookHistory[];
  historyCount = 0
  isEdit = false;

  constructor() {
    this.bookingService.getBookingsByGuestId(this.id)
    .subscribe({
      next: data => {
        this.bookHistory = data;
        this.historyCount = this.bookHistory.length;
      },
      error: err => console.log(err)
    });
  }

}
