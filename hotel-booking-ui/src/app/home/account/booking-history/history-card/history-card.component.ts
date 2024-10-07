import { Component, Input, OnInit } from '@angular/core';
import { BookHistory } from '../../../../ds/booking';

@Component({
  selector: 'app-history-card',
  standalone: true,
  imports: [],
  templateUrl: './history-card.component.html',
  styleUrl: './history-card.component.css'
})
export class HistoryCardComponent implements OnInit {
  @Input()
  history!: BookHistory;

  totalPrice = 0;

  ngOnInit(): void {
    const date1 = new Date(this.history.checkinDate);
    const date2 = new Date(this.history.checkoutDate);
    const days = date2.getTime() - date1.getTime();
    let dayCount = days / (1000 * 3600 * 24);

    this.totalPrice = this.history.price * dayCount;
    this.changePrice(dayCount);
  }

  changePrice(dayCount: number) {
    if (this.history.extra === 1) {
      this.totalPrice += (this.history.price * 0.3) * dayCount;
    }
  }
}
