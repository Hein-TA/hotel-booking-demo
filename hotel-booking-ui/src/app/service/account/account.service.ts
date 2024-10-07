import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../../ds/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  BASE_URL = "http://localhost:8080/hotel-booking/backend/account";

  constructor(private http: HttpClient) { }

  registerAccount(account: Account): Observable<string> {
    return this.http.post<string>(this.BASE_URL + "/register", account);
  }
}
