import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  saveToken(token: string): void {
    localStorage.setItem('jwt', token); // Save the JWT in localStorage
  }

  getToken(): string | null {
    return localStorage.getItem('jwt'); // Retrieve the token when needed
  }

  clearToken(): void {
    localStorage.removeItem('jwt'); // Clear the token on logout
  }
}