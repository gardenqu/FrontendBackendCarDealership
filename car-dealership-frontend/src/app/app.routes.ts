import { RouterModule, Routes } from '@angular/router';
import { SingleVehicleComponentComponent } from './single-vehicle-component/single-vehicle-component.component';
import { VehicleListComponenetComponent } from './vehicle-list-componenet/vehicle-list-componenet.component';
import { AddVehicleComponentComponent } from './add-vehicle-component/add-vehicle-component.component';
import { UpdateVehicleComponentComponent } from './update-vehicle-component/update-vehicle-component.component';
import { NgModule } from '@angular/core';
import { AuthComponent } from './auth/auth.component';


export const routes: Routes = [
    {
        path: 'details/:vin',
        loadComponent: () => import('./single-vehicle-component/single-vehicle-component.component').then(mod=>mod.SingleVehicleComponentComponent)
    },

    {path:'' ,component:AuthComponent},

    {path:'login' ,component:AuthComponent},
    

    {path:'dashboard', component:VehicleListComponenetComponent},

   {path: 'details/:vin/edit' ,loadComponent: () => import('./update-vehicle-component/update-vehicle-component.component').then(mod=>mod.UpdateVehicleComponentComponent)},

   {path:'addVehicle', loadComponent: () => import('./add-vehicle-component/add-vehicle-component.component').then(mod =>mod.AddVehicleComponentComponent)},

   {path:'' ,component:AuthComponent},

    // Wildcard route for undefined paths, redirecting to login page
  { path: '**', redirectTo: '/login', pathMatch: 'full' }  
];



