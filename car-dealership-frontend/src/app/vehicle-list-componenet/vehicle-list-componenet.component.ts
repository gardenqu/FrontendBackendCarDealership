import { NgFor, CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Vehicle } from '../vehicle';
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
  public featured:Vehicle[]=[]

  currentPage: number = 1;
  pageSize: number = 10; // Default page size
  order: string = 'asc'; // Default sorting order
  sortBy: string = 'model'; // Default sorting by model
  searchQuery: string = '';

  filteredVehicles: Vehicle[] = [];
  paginatedVehicles: Vehicle[] = [];
type: any;

  
  constructor(private vehicleService: VehicleServiceService) {} //allows for making request to backend
  ngOnInit(): void {
    this.getVehicles()
    this.getNewVehicles()
    this.getUsedVehicles()
  }

  public getVehicles(): void{
    this.vehicleService.getVehicles()
    .subscribe(vehicles => this.vehicles = vehicles);
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

  searchVehicles(page: number, size: number, order: string, sortBy: string,type:string): void {
    if(type==="new"){
      this.filteredVehicles=this.newVehicles.filter(vehicle =>
        vehicle.models.make.make.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        vehicle.models.modelname.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }else if(type==="used"){
      this.filteredVehicles=this.usedVehicles.filter(vehicle =>
        vehicle.models.make.make.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        vehicle.models.modelname.toLowerCase().includes(this.searchQuery.toLowerCase()))

    }else{
    this.filteredVehicles = this.vehicles.filter(vehicle =>
      vehicle.models.make.make.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      vehicle.models.modelname.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

    
    if (sortBy === 'model') {
      this.filteredVehicles.sort((a, b) => a.models.modelname.localeCompare(b.models.modelname));
    } else if (sortBy === 'make') {
      this.filteredVehicles.sort((a, b) => a.models.make.make.localeCompare(b.models.make.make));
    } else if (sortBy === 'salesprice') {
      this.filteredVehicles.sort((a, b) => a.saleprice - b.saleprice);
    } else if (sortBy === 'msrp') {
      this.filteredVehicles.sort((a, b) => a.msrp - b.msrp);
    }

    if (order === 'dec') {
      this.filteredVehicles.reverse();
  
  
      const startIndex = (page - 1) * size;
      const endIndex = Math.min(startIndex + size, this.filteredVehicles.length);
      this.paginatedVehicles = this.filteredVehicles.slice(startIndex, endIndex);
      
      // Update current page and page size
       this.currentPage = page;
        this.pageSize = size;

  }


  }





}
