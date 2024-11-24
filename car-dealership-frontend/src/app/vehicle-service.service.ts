import { HttpClient, HttpParams, HttpInterceptor } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehicle } from './Entity/vehicle';
import { environment } from '../environments/environment.development';
import { Model } from './Entity/model';
import { Make } from './Entity/make';


@Injectable({
  providedIn: 'root'
})
export class VehicleServiceService {

  constructor(private http:HttpClient) {}
  private apiService=environment.apiUrl
  vehicleMap!: Map<number, Vehicle[]>;

  getListOfVehicles(pageNum: number, size: number, order: string, sortBy: string, isNew: string, searchQuery:string ): Observable<Map<number,Vehicle[]>> {
    let params = new HttpParams()
      .set('pageNum', pageNum.toString())
      .set('size', size.toString())
      .set('order', order)
      .set('sortBy', sortBy)
      .set('isNew', isNew)
      .set('searchQuery', searchQuery); // search for make and model
      


      

    return this.http.get<Map<number,Vehicle[]>>(`${this.apiService}/inventory/search`, { params });
  }

  public getModels(): Observable<Model[]>{
    var listOfModels=this.http.get<Array<Model>>(`${this.apiService}/inventory/models`) // the http service needs a uri to connect it with the backend

    return listOfModels;
  }

  public getMakes(): Observable<Make[]>{
    var listOfMakes=this.http.get<Array<Make>>(`${this.apiService}/inventory/makes`) 

    return listOfMakes;
  }


  public getNewVehicles(): Observable<Vehicle[]>{

  // Make HTTP GET request with parameters
  return this.http.get<Vehicle[]>(`${this.apiService}/inventory/new`)

  }

  public getUsedVehicles(): Observable<Vehicle[]>{
   
    // Make HTTP GET request with parameters
    return this.http.get<Vehicle[]>(`${this.apiService}/inventory/used`)

  }

  public getFeatured(): Observable<Vehicle[]>{
    return this.http.get<Vehicle[]>(`${this.apiService}/inventory/featured`)
  }

  public getVehicle(vin:String): Observable<Vehicle>{
    var vehicle= this.http.get<Vehicle>(`${this.apiService}/inventory/details/${vin}`) // the http service needs a uri to connect it with the backend

    return vehicle;

  }

  public updateVehicle(vin: string, updatedVehicle: Vehicle): Observable<Vehicle>{
    
    return  this.http.put<Vehicle>(`${this.apiService}/inventory/details/${vin}`, updatedVehicle); 
  }


  public addVehicle(vehicle: Vehicle): Observable<Vehicle> {
  
    console.log("URL  "+`${this.apiService}/inventory/addVehicle`)
    return this.http.post<Vehicle>(`${this.apiService}/inventory/addVehicle`, vehicle);
  }


  public deleteVehicle(vin:String): Observable<String> {
    console.log("Delete Vehicle");
    return this.http.delete<String>(`${this.apiService}/inventory/details/${vin}`);
  }

}
