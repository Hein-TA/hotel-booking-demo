import { DatePipe } from '@angular/common';
import { inject, Injectable, OnInit, signal } from '@angular/core';
import { BookingService } from '../booking/booking.service';

@Injectable({
  providedIn: 'root'
})
export class DateService {
  checkinMinDate = signal<string>('');
  checkoutMinDate = signal<string>('');
  checkinDate = signal<string>('');
  checkoutDate = signal<string>('');

  constructor(private datePipe: DatePipe) { 
    const today =  new Date();
    const tomorrow =  new Date();
    tomorrow.setDate(today.getDate() + 1);

    const transformTodayDate = this.datePipe.transform(today, 'yyyy-MM-dd');
    this.checkinMinDate.set(transformTodayDate != null ? transformTodayDate : '');

    const transformTomorrowDate = this.datePipe.transform(tomorrow, 'yyyy-MM-dd');
    this.checkoutMinDate.set(transformTomorrowDate != null ? transformTomorrowDate : '');
   }

   onCheckinChange() {
    let dateString = new Date(this.checkinDate());
    dateString.setDate(dateString.getDate() + 1);
    let dateValue = this.datePipe.transform(dateString, "yyyy-MM-dd");
    this.checkoutMinDate.set(dateValue ? dateValue : '');
    if (this.checkinDate() >= this.checkoutDate()) {
      this.checkoutDate.set(this.checkoutMinDate());
    }
  }
}
