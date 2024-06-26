package com.example.project.Dealership.Controller;

import com.example.project.Dealership.Entity.Make;
import com.example.project.Dealership.Entity.Models;
import com.example.project.Dealership.Entity.Vehicle;
import com.example.project.Dealership.Repository.VehicleRepo;
import com.example.project.Dealership.ServiceLayer.InventorySL;
import com.example.project.Dealership.Util.Constants;
import com.example.project.Dealership.Util.NotFoundException;
import com.example.project.Dealership.Util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    InventorySL inventorySL;

    @Autowired
    VehicleRepo vr;

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }



   /* @GetMapping("all")
    public List<Vehicle> getListOfVehicles(
    ){

        return inventorySL.getAllVehicles();
    }*/

    @GetMapping("makes")
    public List<Make> getMakes(){

        return inventorySL.getMakes();


    }

    @GetMapping("models")
    public List<Models> getModel(){

        return inventorySL.getModels();
    }

    /*@GetMapping("search")
    public List<Vehicle> searchVehicles(){

        return inventorySL.getAllVehicles();
    }*/

    @GetMapping("search")
    public Map<Integer,List<Vehicle>> getListOfVehicles(
            @RequestParam(defaultValue = Pages.DEFAULTPAGENUMBER) int pageNum,
            @RequestParam(defaultValue = Pages.DEFAULTPAGESIZE) int size,
            @RequestParam(defaultValue = Pages.ASC) String order,
            @RequestParam(defaultValue = Pages.SORTBYSALEPRICE) String sortBy,
            @RequestParam(defaultValue = "") String isNew,
            @RequestParam(defaultValue = "") String searchQuery

    ){
        //hold page total and vehicle list
        Map<Integer,List<Vehicle>> items= new HashMap<>();

        Page<Vehicle> vehicles=inventorySL.getSearchedVehicles(pageNum,size,order,sortBy,isNew,searchQuery);
        items.put(vehicles.getTotalPages(),vehicles.toList());

                      //int pageNum, int size, String order, String sortBy, String isNew, String make, St1626ring model
        return items;
    }


    @GetMapping("new")
    public List<Vehicle> getNewVehicles(
            @RequestParam(defaultValue = Constants.NEW) String isNew){


        return inventorySL.GetSearchedVehicles(isNew);
    }

    @GetMapping("used")
    public List<Vehicle> getUsedVehicles( @RequestParam(defaultValue = Constants.USED) String isNew
    ) {
        return inventorySL.GetSearchedVehicles(isNew);
    }

    @GetMapping("details/{id}")
public Vehicle getVehicle(@PathVariable String id){
        System.out.println(id);
        Vehicle vehicle=inventorySL.getVehicle(id);

        if(vehicle==null) throw new NotFoundException("Vehicle of vin "+ id+ " is not found please try again with another vin number");

        return vehicle;

        

    }

    @GetMapping("featured")
    public List<Vehicle> getFeatured(){

       return inventorySL.getFeatured();
    }

    @PostMapping("addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){

        System.out.println("Add Vehicle");



        if(vehicle==null) throw new NotFoundException("Vehicle is blank");



        return inventorySL.addVehicleToDB(vehicle);
    }

    @DeleteMapping("details/{id}")
    public String removeVehicle(@PathVariable String id){

        System.out.println("Vehicle Deleleted");

        Vehicle vehicle=inventorySL.getVehicle(id);

        if(vehicle==null) throw new NotFoundException("Vehicle of vin "+ id+ " is not found please try again with another vin number");

        inventorySL.removeVehicle(id);

        return "{ \"message\": \"Vehicle has been deleted\" }";

    }

    @PutMapping("details/{id}")
    public Vehicle updateVehicleInformation( @PathVariable String id, @RequestBody Vehicle vehicle){


        Vehicle vehicleToBeUpdated=inventorySL.getVehicle(id);

        if(vehicleToBeUpdated==null) throw new NotFoundException("Vehicle of vin "+ id+ " is not found please try again with another vin number");


       return inventorySL.updateVehicleInformation(id,vehicle);


    }



}

