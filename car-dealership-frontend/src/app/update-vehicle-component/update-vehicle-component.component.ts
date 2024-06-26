import { Component, Input, OnInit, input, model } from '@angular/core';
import { SingleVehicleComponentComponent } from "../single-vehicle-component/single-vehicle-component.component";
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { VehicleServiceService } from '../vehicle-service.service';
import { Vehicle } from '../Entity/vehicle';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BehaviorSubject, Observable } from 'rxjs';
import { CommonModule, NgIf } from '@angular/common';
import { VehicleDetailsComponent } from "../vehicle-details/vehicle-details.component";
import { Model } from '../Entity/model';
import { Make } from '../Entity/make';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-update-vehicle-component',
    standalone: true,
    templateUrl: './update-vehicle-component.component.html',
    styleUrl: './update-vehicle-component.component.css',
    imports: [CommonModule, FormsModule, VehicleDetailsComponent]
})
export class UpdateVehicleComponentComponent  implements OnInit{


    
    
    uVehicle!: Vehicle;
    oldVin!: string;
    isEditMode: boolean=true;
    models: Model[]=[];
    filteredModels:Model[]=[]
    makes:Make[]= [];
    selectedMake!: Make;
    constructor(private route: ActivatedRoute,private  vehicleService: VehicleServiceService,private vRoute:Router) { 
        
    }

     
    ngOnInit(): void {
      
        this.oldVin = this.route.snapshot.paramMap.get('vin') ?? 'null';

  
      this.getMakes();
      this.getModels();
  

    }
    

public editVehicle(vin:string, vehicle:Vehicle){
    
   
    this.vehicleService.updateVehicle(vin, vehicle).subscribe(
        (oVehicle: Vehicle)=>{
            console.log('Vehicle updated successfully:', oVehicle);
        }
    )

    

   }


   public receiveVehicle(vehicle: Vehicle) { 
    this.uVehicle = vehicle;
    console.log(vehicle)
  }



  public saveChanges(vehicle:Vehicle){
    
    

    if( !(confirm("are you sure you would like to save you changes?"))){
   window.alert("Your changes have not been saved")
    } 

    this.editVehicle(this.oldVin,vehicle);

    this.vRoute.navigateByUrl(`details/${vehicle.vin}`);

    window.alert("Changes have been saved")
  }

  public getModels(): void{
  
    this.vehicleService.getModels().subscribe(
      (response: Model[])=>{
        this.models=response;
      
      },(error:HttpErrorResponse)=>alert(error.message)
    );
  }



  public getFilteresModels(make:Make): Model[]{

    console.log("The Make: "+ make.make)

  

    console.log(this.models)
    return this.models

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
