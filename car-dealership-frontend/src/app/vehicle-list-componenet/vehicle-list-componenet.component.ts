import { NgFor, CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Vehicle } from '../Entity/vehicle';
import { VehicleServiceService } from '../vehicle-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormsModule, NgModel, NumberValueAccessor } from '@angular/forms';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-vehicle-list-componenet',
  standalone: true,
  imports: [NgFor,CommonModule,RouterLink,RouterLinkActive,FormsModule],
  templateUrl: './vehicle-list-componenet.component.html',
  styleUrl: './vehicle-list-componenet.component.css'
})
export class VehicleListComponenetComponent implements OnInit {

  
  vehicles:Vehicle[]=[];
  newVehicles:Vehicle[]=[];
  usedVehicles:Vehicle[]=[];
  tempList:Vehicle[]=[];
  filteredVehicles: Vehicle[] = [];


  pageNum: number=0
  pageSize: number =10
  pageRange: number[] = [];
  order: string ="asc"
  sortBy: string="Model"
  searchQuery: string = "";
  type:string=""
  make:string=""
  model:string=""
  

  

  
  constructor(private vehicleService: VehicleServiceService) {} //allows for making request to backend
  ngOnInit(): void {
    
    this.searchVehicles()
    this.getNewVehicles()
    
  }

  public getNewVehicles():void{
    this.vehicleService.getNewVehicles().subscribe( 
      (response: Vehicle[])=>{
        this.newVehicles=response;
        console.log(this.newVehicles)
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );

  }

  public getFeaturedVehicles():Vehicle[]{
    return this.newVehicles.filter(vehicle => vehicle.featured==true)
  }





  public searchVehicles(): void {
    this.vehicleService.getListOfVehicles(this.pageNum, this.pageSize, this.order, this.sortBy, this.type, this.searchQuery)
      .subscribe((value: Map<number, Vehicle[]>) => {
        const totalPages = Object.keys(value)[0]; // Assuming total pages is the first key
        let vehicles = Object.values(value)[0]; // Convert totalPages to number
  
        // Update component properties
        if (vehicles !== undefined) {
          this.pageRange =  Array.from({ length: +totalPages }, (_, i) => i + 1); //sets pageRange to an array using the total pages
          console.log(this.pageRange)
          this.vehicles = vehicles;
          console.log( vehicles);
        } else {
          console.error("Received undefined vehicles from the API.");
        }
      });
  }



}
