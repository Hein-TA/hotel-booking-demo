import { Component, EventEmitter, inject, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../service/auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule, Location } from '@angular/common';
import { GuestService } from '../../../service/guest/guest.service';
import { Guest } from '../../../ds/guest';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent implements OnInit {
  router = inject(Router);
  authService = inject(AuthService);
  currentPath!: string;

  @Output()
  cancelEventEmitter = new EventEmitter<any>();

  @Output()
  showRegisterEventEmitter = new EventEmitter<any>();
  passwordErr = '';

  credential = {
    username: '',
    password: ''
  }

  constructor(private location: Location) { }

  ngOnInit(): void {
    this.currentPath = this.location.path();
  }

  cancelEvent() {
    this.cancelEventEmitter.emit();
  }

  showRegisterFormNow() {
    this.showRegisterEventEmitter.emit();
  }

  clearPasswordErr() {
    this.passwordErr = '';
  }

  login() {
    this.authService.login(this.credential)
    .subscribe({
      next: data => {
        this.authService.setToken(data.accessToken);
        sessionStorage.setItem("user", this.credential.username);
      },
      error: err => {
        console.log(err);
        this.passwordErr = 'Wrong Password!';
      },
      complete: () => {
        this.cancelEvent();
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate([this.currentPath]);
        });
      }
    });
  }
}
