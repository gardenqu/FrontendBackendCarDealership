import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../Entity/vehicle';
import { CommonModule } from '@angular/common';
import { FormsModule, NgModelGroup } from '@angular/forms';
import { VehicleServiceService } from '../vehicle-service.service';
import { Router } from '@angular/router';
import { Model } from '../Entity/model';
import { Make } from '../Entity/make';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-vehicle-component',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './add-vehicle-component.component.html',
  styleUrl: './add-vehicle-component.component.css'
})
export class AddVehicleComponentComponent  implements OnInit{

  newVehicle: any = { model: { make: {} } };


  makes:Make[]= [];
  models:Model[]=[]
  filteredModels: Model[]=[];

  constructor( private  vehicleService: VehicleServiceService,private vRoute:Router) { }

  ngOnInit(): void {
    this.getMakes()
    this.getModels()
  }

  

  addVehicle() {
    console.log(this.newVehicle);
    if (confirm("Are you sure you would like to add this vehicle?")) {
      this.vehicleService.addVehicle(this.newVehicle).subscribe(
        () => {
          // Success callback: vehicle added successfully
          window.alert("Vehicle has been added");
          console.log("Vehicle added successfully");
          
          // Reset form after successful addition
          this.newVehicle = new Vehicle();
        },
        error => {
          // Error callback: handle error
          console.error("Error adding vehicle:", error);
          window.alert("An error occurred while adding the vehicle. Please try again later.");
        }
      );
    } else {
      // User canceled the operation
      window.alert("The vehicle was not added");
    }
  }





  public getModels(): void{
    this.vehicleService.getModels().subscribe(
      (response: Model[])=>{
        this.models=response;
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );
  }


  public getMakes(): void{
    this.vehicleService.getMakes().subscribe(
      (response: Make[])=>{
        this.makes=response;
        console.log(this.makes)
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );
  }


  onMakeSelection(make: Make) {
  

    console.log("Does it do? "+ make.make)
    console.log(this.models)
 
    this.filteredModels=this.models.filter( model => model.make.make===make.make)
 
    console.log(this.filteredModels)
 
   }
 
 

}
