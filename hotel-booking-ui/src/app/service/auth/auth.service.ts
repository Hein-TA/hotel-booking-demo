import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtResponse } from '../../ds/jwtResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http:HttpClient) { }

login(credential: any): Observable<JwtResponse> {
  const loginDto = {
    email: credential.username,
    password: credential.password
  }

  const httpHeaders = new HttpHeaders();
  httpHeaders.set("Content-Type", "application/json");
  return this.http.post<JwtResponse>("http://localhost:8080/hotel-booking/backend/account/login", loginDto, {headers: httpHeaders});
}

setToken(token: string): void {
  localStorage.setItem("access_token", token);
}

getToken(): string | null {
  return localStorage.getItem("access_token");
}

getLoggedUser(): string | null {
  return sessionStorage.getItem("user");
}

isUserLogin(): boolean {
  return sessionStorage.getItem("user") !== null;
}

}
