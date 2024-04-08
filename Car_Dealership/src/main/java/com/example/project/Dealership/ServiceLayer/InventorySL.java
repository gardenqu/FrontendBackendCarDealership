package com.example.project.Dealership.ServiceLayer;

import com.example.project.Dealership.Entity.Make;
import com.example.project.Dealership.Entity.Models;
import com.example.project.Dealership.Entity.Vehicle;
import com.example.project.Dealership.Repository.MakeRepo;
import com.example.project.Dealership.Repository.ModelRepo;
import com.example.project.Dealership.Repository.VehicleRepo;
import com.example.project.Dealership.Util.NotFoundException;
import com.example.project.Dealership.Util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventorySL {

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    MakeRepo makeRepo;

    @Autowired
    ModelRepo modelRepo;

    public List<Vehicle> getAllVehicles(){


        return vehicleRepo.findAll();
    }

    public List<Vehicle>GetSearchedVehicles(String isNew){


        return vehicleRepo.findAll().stream().filter(p ->
                p.isNew()==Boolean.parseBoolean(isNew)).toList();

    }

    private Pageable getSortOrder(int pageNum,int size,String order,String sortBy){
        Pageable paging=null;

        if(order.equals(Pages.ASC)){
            paging= PageRequest.of(pageNum, size, Sort.by(sortBy).ascending());

        }else{
            paging= PageRequest.of(pageNum, size,Sort.by(sortBy).descending());

        }
        return paging;
    }

    public Vehicle getVehicle(String id) throws NotFoundException {


        return vehicleRepo.getVehicleByVin(id);
    }

public Vehicle addVehicleToDB(Vehicle vehicle){

        vehicleRepo.save(vehicle);

        return vehicleRepo.getVehicleByVin(vehicle.getVin());
}

public void removeVehicle(String vin){

    Vehicle vehicle= vehicleRepo.getVehicleByVin(vin);
    vehicleRepo.delete(vehicle);
}

public Vehicle updateVehicleInformation(String vin, Vehicle vehicle){
        ///vin, modelid, color, type, bodystyle, transmission, interior, year, msrp, saleprice, mileage, description, photo, featured, issold
    Vehicle beforeUpdate= vehicleRepo.getVehicleByVin(vin);

    beforeUpdate.setVin(vehicle.getVin());
    beforeUpdate.setModel(vehicle.getModel());
    beforeUpdate.setColor(vehicle.getColor());
    beforeUpdate.setNew(vehicle.isNew());
    beforeUpdate.setBodystyle(vehicle.getBodystyle());
    beforeUpdate.setTransmission(vehicle.getTransmission());
    beforeUpdate.setInterior(vehicle.getInterior());
    beforeUpdate.setYear(vehicle.getYear());
    beforeUpdate.setMsrp(vehicle.getMsrp());
    beforeUpdate.setSaleprice(vehicle.getSaleprice());
    beforeUpdate.setMileage(vehicle.getMileage());
    beforeUpdate.setDescription(vehicle.getDescription());
    beforeUpdate.setFeatured(vehicle.isFeatured());
    beforeUpdate.setPhoto(vehicle.getPhoto());
    beforeUpdate.setFeatured(vehicle.isFeatured());
    beforeUpdate.setIssold(vehicle.isIssold());

    vehicleRepo.save(beforeUpdate);
    return beforeUpdate;
}


public List<Make> getMakes(){
        return makeRepo.findAll();

}

public List<Models> getModels(){

        return modelRepo.findAll();
}

public List<Vehicle>getFeatured(){

    List<Vehicle> vehicle= vehicleRepo.findAll();
    return vehicle.stream().filter(Vehicle::isFeatured).collect(Collectors.toList());


}



}
