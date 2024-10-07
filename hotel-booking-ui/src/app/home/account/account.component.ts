import { AfterContentInit, AfterViewInit, ChangeDetectorRef, Component, inject, OnInit, signal, viewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { BookingNavbarComponent } from "../booking-navbar/booking-navbar.component";
import { GuestService } from '../../service/guest/guest.service';
import { SideContentComponent } from "./side-content/side-content.component";

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [RouterModule, BookingNavbarComponent, SideContentComponent],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent {

}
