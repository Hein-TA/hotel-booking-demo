import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BookingNavbarComponent } from "../booking-navbar/booking-navbar.component";

@Component({
  selector: 'app-booking-page',
  standalone: true,
  imports: [RouterModule, BookingNavbarComponent],
  templateUrl: './booking-page.component.html',
  styleUrl: './booking-page.component.css'
})
export class BookingPageComponent {

}
