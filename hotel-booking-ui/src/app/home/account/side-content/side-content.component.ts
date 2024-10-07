import { Component, inject, OnInit } from '@angular/core';
import { GuestService } from '../../../service/guest/guest.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-side-content',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './side-content.component.html',
  styleUrl: './side-content.component.css'
})
export class SideContentComponent {
  router = inject(Router);
  guestService = inject(GuestService);
  guest = this.guestService.guest;

  logout() {
    sessionStorage.clear();
    localStorage.clear();
    this.guestService.guest.set(this.guestService.dummyGuest);
    this.router.navigate(['/booking/search-room']);
  }
}
