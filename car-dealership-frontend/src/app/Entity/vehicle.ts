import { Make } from "./make";
import { Model } from "./model";

export class Vehicle {

    vehicleid!: number;
    vin!: string;

    model!:Model

    color!: string;

    new!: Boolean;

    bodystyle!: string;

    transmission!: number;

    interior!: string;

     year!: number;

     msrp!: number;

     mileage!: number;

     description!: string;

    photo!: string;

    featured!: boolean;
    
    issold!: boolean;
    
    saleprice!: number;

}
