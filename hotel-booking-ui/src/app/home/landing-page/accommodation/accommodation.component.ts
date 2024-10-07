import { ViewportScroller } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-accommodation',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './accommodation.component.html',
  styleUrl: './accommodation.component.css'
})
export class AccommodationComponent {
  constructor(private viewportScroller: ViewportScroller) {
    this.viewportScroller.scrollToPosition([0, 0])
  }
}
