import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development'; // Make sure to adjust your API URL
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = environment.apiUrl;  // Define your API URL here

  constructor(private http: HttpClient, private router: Router) { }

  // Method to login a user
  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post<any>(`${this.apiUrl}/auth/login`, body);
  }

  // Register method with full UserEntity object
  register(userEntity: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/auth/register`, userEntity);
  }


  // Save JWT token in localStorage
  saveToken(token: string) {
    localStorage.setItem('accessToken', token);
  }

  // Get JWT token from localStorage
  getToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  // Log out the user by clearing the stored token
  logout() {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']); // Redirect to login page after logout
  }

  // Check if the user is logged in (based on the token)
  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
