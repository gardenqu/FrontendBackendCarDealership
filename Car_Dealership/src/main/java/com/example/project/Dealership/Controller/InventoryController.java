package com.example.project.Dealership.Controller;

import com.example.project.Dealership.Entity.*;
import com.example.project.Dealership.Repository.UserEntityRepo;
import com.example.project.Dealership.Repository.VehicleRepo;
import com.example.project.Dealership.ServiceLayer.InventorySL;
import com.example.project.Dealership.Util.Constants;
import com.example.project.Dealership.Util.NotFoundException;
import com.example.project.Dealership.Util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    InventorySL inventorySL;

    @Autowired
    VehicleRepo vr;

    @Autowired
    UserEntityRepo ur;

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }



    @GetMapping("/basicauth")
    public String feedback(
    ){

        return "You are authenticated ";
    }

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
    public Map<Integer, List<VehicleDTO>> getListOfVehicles(
            @RequestParam(defaultValue = Pages.DEFAULTPAGENUMBER) int pageNum,
            @RequestParam(defaultValue = Pages.DEFAULTPAGESIZE) int size,
            @RequestParam(defaultValue = Pages.ASC) String order,
            @RequestParam(defaultValue = Pages.SORTBYSALEPRICE) String sortBy,
            @RequestParam(defaultValue = "") String isNew,
            @RequestParam(defaultValue = "") String searchQuery
    ) {
        // Hold page total and vehicle list
        Map<Integer, List<VehicleDTO>> items = new HashMap<>();

        // Get the Page of VehicleDTO objects directly
        Page<VehicleDTO> vehicleDTOsPage = inventorySL.getSearchedVehicles(pageNum, size, order, sortBy, isNew, searchQuery);

        // Convert the list of VehicleDTOs to a list
        items.put(vehicleDTOsPage.getTotalPages(), vehicleDTOsPage.getContent());

        return items;
    }


    @GetMapping("new")
    public List<VehicleDTO> getNewVehicles(
            @RequestParam(defaultValue = Constants.NEW) String isNew){


        return inventorySL.GetSearchedVehicles(isNew);
    }

    @GetMapping("used")
    public List<VehicleDTO> getUsedVehicles( @RequestParam(defaultValue = Constants.USED) String isNew
    ) {
        return inventorySL.GetSearchedVehicles(isNew);
    }

    @GetMapping("details/{id}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable String id) {
        System.out.println(id);

        // Get the Optional<VehicleDTO> from the service layer
        Optional<VehicleDTO> vehicleOptional = inventorySL.getVehicle(id);

        // If the vehicle is not found, throw NotFoundException
        VehicleDTO vehicleDTO = vehicleOptional.orElseThrow(() -> new NotFoundException("Vehicle of vin " + id + " is not found, please try again with another vin number"));

        // Return the found VehicleDTO with HTTP status 200 OK
        return ResponseEntity.ok(vehicleDTO);
    }



    @GetMapping("featured")
    public List<Vehicle> getFeatured() {
        return inventorySL.getFeatured();
    }

    @PostMapping("addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle, Principal principal) {
        System.out.println("Add Vehicle");

        if (vehicle == null) throw new NotFoundException("Vehicle is blank");

        UserEntity currentUser = ur.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return inventorySL.addVehicleToDB(vehicle, currentUser)
                .orElseThrow(() -> new NotFoundException("Failed to add vehicle"));
    }

    @DeleteMapping("details/{id}")
    public String removeVehicle(@PathVariable String id) {
        System.out.println("Vehicle Deleted");

        // Get the Optional<Vehicle> from the service layer
        Optional<VehicleDTO> vehicleOptional = inventorySL.getVehicle(id);

        // If the vehicle is not found, throw NotFoundException
        vehicleOptional.orElseThrow(() -> new NotFoundException("Vehicle of vin " + id + " is not found, please try again with another vin number"));

        inventorySL.removeVehicle(id);

        return "{ \"message\": \"Vehicle has been deleted\" }";
    }

    @PutMapping("details/{id}")
    public VehicleDTO updateVehicleInformation(@PathVariable String id, @RequestBody VehicleDTO vehicleDTO) {
        // Get the Optional<Vehicle> from the service layer
        Optional<VehicleDTO> vehicleOptional = inventorySL.getVehicle(id);

        // If the vehicle is not found, throw NotFoundException
        VehicleDTO vehicleToBeUpdated = vehicleOptional.orElseThrow(() -> new NotFoundException("Vehicle of vin " + id + " is not found, please try again with another vin number"));

        // Map the VehicleDTO back to a Vehicle entity (you can add a method for this conversion if necessary)
        Vehicle vehicle = inventorySL.convertToEntity(vehicleDTO);

        // Update the vehicle
        return inventorySL.convertToDTO(inventorySL.updateVehicleInformation(id, vehicle).orElseThrow(() -> new NotFoundException("Failed to update vehicle with vin " + id)));
    }





}

