import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterOutlet, Routes, provideRouter } from '@angular/router';
import { VehicleServiceService } from '../vehicle-service.service';
import { Vehicle } from '../Entity/vehicle';
import { CommonModule } from '@angular/common';
import { Observable, of } from 'rxjs';
import { UpdateVehicleComponentComponent } from "../update-vehicle-component/update-vehicle-component.component";
import { VehicleDetailsComponent } from "../vehicle-details/vehicle-details.component";

@Component({
    selector: 'app-single-vehicle-component',
    standalone: true,
    templateUrl: './single-vehicle-component.component.html',
    styleUrl: './single-vehicle-component.component.css',
    imports: [CommonModule, RouterOutlet, UpdateVehicleComponentComponent, VehicleDetailsComponent]
})
export class SingleVehicleComponentComponent implements OnInit {

 
  

  vin!: string;
  

  constructor(private route: ActivatedRoute, private editRoute:Router, private  vehicleService: VehicleServiceService) {
   }




 
  ngOnInit(): void {
    console.log("IN SINGLE VEHICLE")
    console.log("SV VIN" + this.vin)
    
  }

  public deleteVehicle() {
    if (confirm("Are you sure you would like to delete this vehicle?")) {
      this.vehicleService.deleteVehicle(this.vin)
        .subscribe(() => {
          window.alert("Vehicle has been deleted");
          this.editRoute.navigateByUrl('');
        }, error => {
          console.error("Error deleting vehicle:", error);
          window.alert("An error occurred while deleting the vehicle. Please try again later.");
        });
    } else {
      window.alert("Vehicle was not deleted");
    }
  }


  public receiveVehicle(pVin:Vehicle) { // Change the parameter type to Vehicle
    this.vin = pVin.vin;
    console.log("VIN NUM: "+ pVin.vin)
  }


  public toEdit(){

    // Redirect to a different page
    this.editRoute.navigateByUrl(`details/${this.vin}/edit`);


  }




}


