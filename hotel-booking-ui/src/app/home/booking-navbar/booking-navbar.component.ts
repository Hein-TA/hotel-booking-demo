import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { RegisterFormComponent } from "./register-form/register-form.component";
import { LoginFormComponent } from "./login-form/login-form.component";
import { AuthService } from '../../service/auth/auth.service';
import { Location } from '@angular/common';
import { GuestService } from '../../service/guest/guest.service';

@Component({
  selector: 'app-booking-navbar',
  standalone: true,
  imports: [RouterModule, RegisterFormComponent, LoginFormComponent],
  templateUrl: './booking-navbar.component.html',
  styleUrl: './booking-navbar.component.css'
})
export class BookingNavbarComponent implements OnInit {
  router = inject(Router);
  authService = inject(AuthService);
  guestService = inject(GuestService);
  location = inject(Location);

  showRegisterForm: boolean = false;
  showLoginForm: boolean = false;
  currentPath!: string;
  guest = this.guestService.guest;

  isUserLoggedIn: boolean = false;
  userMenu: boolean = false

  ngOnInit(): void {
    if(this.authService.isUserLogin()) {
      this.isUserLoggedIn = !this.isUserLoggedIn;
      this.guestService.findGuestByEmail(this.authService.getLoggedUser())
        .subscribe({
          next: data => this.guestService.guest.set(data)
        });
    }
  }

  logout() {
    sessionStorage.clear();
    localStorage.clear();
    this.guestService.guest.set(this.guestService.dummyGuest);
    this.router.navigateByUrl("/", { skipLocationChange: true }).then(() => {
      if (this.location.path() === '/account/info' || this.location.path() === '/account/history') {
        this.router.navigate(['/booking/search-room']);
      }
      else {
        this.router.navigate([this.location.path()]);
      }
    });
  }

  toggleDropDown() {
    this.userMenu = !this.userMenu;
  }

  showRegisterFormNow() {
    this.cancelNow();
    this.showRegisterForm = true;
    document.body.style.overflow = 'hidden';
  }

  showLoginFormNow() {
    this.showRegisterForm = false;
    this.showLoginForm = true;
    document.body.style.overflow = 'hidden';
  }

  cancelNow() {
    this.showRegisterForm = false;
    this.showLoginForm = false;
    document.body.style.overflow = 'auto';
  }
}
