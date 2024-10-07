import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Account } from '../../../ds/account';
import { AccountService } from '../../../service/account/account.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.css'
})
export class RegisterFormComponent {
  accountService: AccountService = inject(AccountService);
  errorMsg = '';
  isChecked = false;

  setErrorMsg() {
    this.errorMsg = '';
  }

  @Output()
  loginEventEmitter: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  cancelEventEmitter: EventEmitter<any> = new EventEmitter<any>();

  account: Account = {
    firstName: '',
    lastName: '',
    birthDate: '',
    email: '',
    password: '',
    phone: '',
    street: '',
    city: '',
    country: ''
  };
  
  cancelEvent() {
    this.cancelEventEmitter.emit();
  }

  showLoginFormNowEvent() {
    console.log(this.account);
    console.log(this.errorMsg);
    this.accountService.registerAccount(this.account)
    .subscribe({
      next: data => console.log(data),
      error: err => {
        console.log(err);
        this.errorMsg = err.error;
      },
      complete: () => this.loginEventEmitter.emit()
    });
  }
}
