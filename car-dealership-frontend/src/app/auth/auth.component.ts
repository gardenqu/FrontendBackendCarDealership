import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {


  password!: string;
  firstname!: string;
  lastname!: string;
  confirmPassword: string = '';
  role: string = 'USER'; // Default role
  email!: string;
  errorMessage!: string;
  isLoginMode: boolean = true;



  constructor(private authService: AuthService, private router: Router) { }

  // Toggle between login and register forms
  toggleMode() {
    this.isLoginMode = !this.isLoginMode;
    this.errorMessage = ''; // Clear any previous error message
  }

  // Handle user login
  onLogin() {
    this.authService.login(this.email, this.password).subscribe(
      response => {
        this.authService.saveToken(response.accessToken); // Save the JWT token
        this.router.navigate(['/dashboard']);       // Redirect to the dashboard
      },
      error => {
        this.errorMessage = 'Invalid credentials. Please try again.';
      }
    );
  }

  // Handle user registration
   onRegister() {
    const userEntity = {
      email: this.email,
      password: this.confirmPassword,
      firstname: this.firstname,
      lastname: this.lastname,
      role: this.role
    };

    this.authService.register(userEntity).subscribe(
      response => {
        this.authService.saveToken(response.accessToken);  // Save the JWT token
        this.router.navigate(['/dashboard']);  // Redirect to the dashboard or home page
      },
      error => {
        this.errorMessage  = `Server error: ${error.error}`;
      }
    );
  }

}
