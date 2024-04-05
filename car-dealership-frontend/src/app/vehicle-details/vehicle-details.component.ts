import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Vehicle } from '../vehicle';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { VehicleServiceService } from '../vehicle-service.service';

@Component({
  selector: 'app-vehicle-details',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './vehicle-details.component.html',
  styleUrl: './vehicle-details.component.css'
})
export class VehicleDetailsComponent {

  @Output() uVehicle= new EventEmitter<Vehicle>();
  
 vin!: string;
 vehicle$!: Observable<Vehicle>;


  constructor(private route: ActivatedRoute, private editRoute:Router, private  vehicleService: VehicleServiceService) {
   }



 
  ngOnInit(): void {
    
    this.vin=this.route.snapshot.paramMap.get('vin') ?? 'null';
    
    console.log("D VIN:", this.vin);
    this.getVehicle(this.vin)

    
  }



  

  public getVehicle(id:string ):void{

    //retrives observable vehichle
    this.vehicle$ = this.vehicleService.getVehicle(id);
    console.log(this.vehicle$);

    this.vehicle$.subscribe(vehicle => {
      this.uVehicle.emit(vehicle); // Emit the Vehicle object directly, Output wont work otherwise
      console.log("INSIDE details", vehicle);
    });
  } 


}
