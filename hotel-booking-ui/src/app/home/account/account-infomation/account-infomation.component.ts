import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Output } from '@angular/core';
import { GuestService } from '../../../service/guest/guest.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-infomation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './account-infomation.component.html',
  styleUrl: './account-infomation.component.css'
})
export class AccountInfomationComponent {
  guestService = inject(GuestService);
  router = inject(Router);
  guest = this.guestService.guest;

  isEdit = false;

  editNow() {
    this.isEdit = !this.isEdit;
  }

  updateGuest() {
    this.guestService.updateGuestInfo(this.guest())
    .subscribe({
      next: data => console.log(data),
      error: err => console.log(err),
      complete: () => this.isEdit = !this.isEdit
    });
  }
}
