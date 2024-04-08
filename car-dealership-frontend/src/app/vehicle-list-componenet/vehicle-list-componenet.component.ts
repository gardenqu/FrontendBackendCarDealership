import { NgFor, CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Vehicle } from '../Entity/vehicle';
import { VehicleServiceService } from '../vehicle-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-vehicle-list-componenet',
  standalone: true,
  imports: [NgFor,CommonModule,RouterLink,RouterLinkActive,FormsModule],
  templateUrl: './vehicle-list-componenet.component.html',
  styleUrl: './vehicle-list-componenet.component.css'
})
export class VehicleListComponenetComponent implements OnInit {

  public vehicles:Vehicle[]=[];
  public newVehicles:Vehicle[]=[];
  public usedVehicles:Vehicle[]=[];

  currentPage: number = 1;
  pageSize: number = 10; // Default page size
  order: string = 'asc'; // Default sorting order
  sortBy: string = 'model'; // Default sorting by model
  searchQuery: string = "";

  filteredVehicles: Vehicle[] = [];
  paginatedVehicles: Vehicle[] = [];
type: any;

  
  constructor(private vehicleService: VehicleServiceService) {} //allows for making request to backend
  ngOnInit(): void {
    
    this.getVehicles()
    this.getNewVehicles()
    this.getUsedVehicles()
    this.searchVehicles()
  }

  public getVehicles(): void{
    this.vehicleService.getVehicles()
    .subscribe(vehicles => this.vehicles = vehicles);
    console.log(this.vehicles)
  }

  public getNewVehicles():void{
    this.vehicleService.getNewVehicles().subscribe( 
      (response: Vehicle[])=>{
        this.newVehicles=response;
        console.log(this.newVehicles)
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );

  }


  public getFeaturedVehicles(page: number, size: number, order: string, sortBy: string):void{
    this.vehicleService.getFeatured().subscribe( 
      (response: Vehicle[])=>{
        this.newVehicles=response;
        console.log(this.newVehicles)
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );

  }

  public getUsedVehicles():void{
    this.vehicleService.getUsedVehicles().subscribe(
      (response: Vehicle[])=>{
        this.usedVehicles=response;
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );
  }

  searchVehicles( page: number = 1, size: number = 10, order: string = 'asc', sortBy: string = 'make', type: string = 'all'): void {
    console.log("Upon load");
    
    // Filter vehicles based on type
    if (type === 'new') {
      this.filteredVehicles = this.newVehicles.slice(); // newVehicles contains default new vehicles
    } else if (type === 'used') {
      this.filteredVehicles = this.usedVehicles.slice(); // usedVehicles contains default used vehicles
    } else {
      this.filteredVehicles = this.vehicles.slice(); // vehicles contains default vehicles
    }
    
    // Filter based on search query
    this.filteredVehicles = this.filteredVehicles.filter(vehicle =>
      vehicle.model.make.make.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      vehicle.model.modelname.toLowerCase().includes(this.searchQuery.toLowerCase())
    );

    // Sort filtered vehicles
    if (sortBy === 'model') {
      this.filteredVehicles.sort((a, b) => a.model.modelname.localeCompare(b.model.modelname));
    } else if (sortBy === 'make') {
      this.filteredVehicles.sort((a, b) => a.model.make.make.localeCompare(b.model.make.make));
    } else if (sortBy === 'salesprice') {
      this.filteredVehicles.sort((a, b) => a.saleprice - b.saleprice);
    } else if (sortBy === 'msrp') {
      this.filteredVehicles.sort((a, b) => a.msrp - b.msrp);
    }

    // Reverse if order is descending
    if (order === 'desc') {
      this.filteredVehicles.reverse();
    }

    // Paginate filtered vehicles
    const startIndex = (page - 1) * size;
    const endIndex = Math.min(startIndex + size, this.filteredVehicles.length);
    this.vehicles = this.filteredVehicles.slice(startIndex, endIndex);

    // Update current page and page size
    this.currentPage = page;
    this.pageSize = size;
  }




}
