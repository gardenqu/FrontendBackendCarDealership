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

  vehicles:Vehicle[]=[];
   newVehicles:Vehicle[]=[];
  usedVehicles:Vehicle[]=[];
  tempList:Vehicle[]=[];
  filteredVehicles: Vehicle[] = [];

  currentPage: number=1
  pageSize: number =10;
  order: string ="asc"
  sortBy: string="Model"
  searchQuery: string = "";
  type:String="all"

  

  
  constructor(private vehicleService: VehicleServiceService) {} //allows for making request to backend
  ngOnInit(): void {
    
    this.getVehicles()
    this.getNewVehicles()
    this.getUsedVehicles()
    
  }

  public getVehicles(): void{
    this.vehicleService.getVehicles()
    .subscribe(vehicles => this.vehicles = vehicles);
    console.log(this.vehicles)
  }

  public getFeaturedVehicles():Vehicle[]{
    return this.newVehicles.filter(vehicle => vehicle.featured==true)
  }

  public getNewVehicles():void{
    this.vehicleService.getNewVehicles().subscribe( 
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

  searchVehicles( ): void {
    console.log("Upon load");
    
    // Filter vehicles based on type
    if (this.type === 'new') {
      this.filteredVehicles = this.newVehicles; // newVehicles contains default new vehicles
    } else if (this.type === 'used') {
      this.filteredVehicles = this.usedVehicles; // usedVehicles contains default used vehicles
    } else {
      if(this.tempList.length==0){
        this.tempList=this.vehicles
      }
      this.vehicles=this.tempList
      this.filteredVehicles = this.vehicles; // vehicles contains default vehicles
    }
    
    // Filter based on search query
    this.filteredVehicles = this.filteredVehicles.filter(vehicle =>
      vehicle.model.make.make.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      vehicle.model.modelname.toLowerCase().includes(this.searchQuery.toLowerCase())
    );

    // Sort filtered vehicles
    if (this.sortBy === 'model') {
      this.filteredVehicles.sort((a, b) => a.model.modelname.localeCompare(b.model.modelname));
    } else if (this.sortBy === 'make') {
      this.filteredVehicles.sort((a, b) => a.model.make.make.localeCompare(b.model.make.make));
    } else if (this.sortBy === 'salesprice') {
      this.filteredVehicles.sort((a, b) => a.saleprice - b.saleprice);
    } else if (this.sortBy === 'msrp') {
      this.filteredVehicles.sort((a, b) => a.msrp - b.msrp);
    }

    // Reverse if order is descending
    if (this.order === 'desc') {
      this.filteredVehicles.reverse();
    }

    console.log(typeof +this.pageSize)

    // Paginate filtered vehicles
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + +this.pageSize;
    this.vehicles = this.filteredVehicles.slice(startIndex, endIndex);

  }
  




}
