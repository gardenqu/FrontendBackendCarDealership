import { Component, Injectable, OnInit } from '@angular/core';
import { PreloadAllModules, RouterLink, RouterLinkActive, RouterOutlet, provideRouter, withDebugTracing, withPreloading } from '@angular/router';
import { Vehicle } from './Entity/vehicle';
import { VehicleServiceService } from './vehicle-service.service';
import { NgFor, CommonModule } from '@angular/common';
import { HttpErrorResponse, provideHttpClient } from '@angular/common/http';
import { bootstrapApplication } from '@angular/platform-browser';
import { routes } from './app.routes';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RouterLink,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {
  title = 'car-dealership';

 
  
  ngOnInit(): void {
    
   // this.getVehicle('your-vehicle-id')
  }

  
  /*public getVehicle(id:string ):void{

    this.vehicleService.getVehicle(id).subscribe(
      (response:Vehicle)=>{
        this.vehicle=response
      }
    )
  } */
  //i think we might need a vehicle component for this

}

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    // provider to inject routes, preload all modules and trace route change events
    provideRouter(routes, withPreloading(PreloadAllModules), withDebugTracing())
  ]
});
