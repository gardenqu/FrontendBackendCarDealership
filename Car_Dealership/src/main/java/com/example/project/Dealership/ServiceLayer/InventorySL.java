package com.example.project.Dealership.ServiceLayer;

import com.example.project.Dealership.Entity.*;
import com.example.project.Dealership.Repository.MakeRepo;
import com.example.project.Dealership.Repository.ModelRepo;
import com.example.project.Dealership.Repository.VehicleRepo;
import com.example.project.Dealership.Util.NotFoundException;
import com.example.project.Dealership.Util.Pages;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventorySL {

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    MakeRepo makeRepo;

    @Autowired
    ModelRepo modelRepo;

    /*public List<Vehicle> getAllVehicles(){


        return vehicleRepo.findAll();
    }*/


    public Page<VehicleDTO> getSearchedVehicles(int pageNum, int size, String order, String sortBy, String isNew, String searchQuery) {
        Pageable paging = getSortOrder(pageNum, size, order, sortBy);

        Specification<Vehicle> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (isNew != null && !isNew.isEmpty()) {
                boolean isNewBoolean = Boolean.parseBoolean(isNew);
                predicates.add(cb.equal(root.get("isNew"), isNewBoolean));
            }

            if (searchQuery != null && !searchQuery.isEmpty()) {
                String likePattern = "%" + searchQuery.toLowerCase() + "%";
                Predicate makePredicate = cb.like(cb.lower(root.get("model").get("make").get("make")), likePattern);
                Predicate modelPredicate = cb.like(cb.lower(root.get("model").get("modelname")), likePattern);
                predicates.add(cb.or(makePredicate, modelPredicate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Vehicle> vehiclesPage = vehicleRepo.findAll(spec, paging);

        // Convert each vehicle in the Page to a VehicleDTO
        Page<VehicleDTO> vehicleDTOPage = vehiclesPage.map(vehicle -> convertToDTO(vehicle));

        return vehicleDTOPage;
    }




    public List<VehicleDTO> GetSearchedVehicles(String isNew) {
        return vehicleRepo.findAll().stream()
                .filter(p -> p.isNew() == Boolean.parseBoolean(isNew))
                .map(vehicle -> convertToDTO(vehicle)) // Convert Vehicle to VehicleDTO
                .collect(Collectors.toList());
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



    public Optional<VehicleDTO> getVehicle(String vin) {
        Optional<Vehicle> vehicleOptional = vehicleRepo.getVehicleByVin(vin);

        // Use the convertToDTO method to map the Vehicle to VehicleDTO
        return vehicleOptional.map(vehicle -> convertToDTO(vehicle));
    }
    public Optional<Vehicle> addVehicleToDB(Vehicle vehicle, UserEntity currentUser) {
        vehicle.setUserEntity(currentUser);
        vehicleRepo.save(vehicle);
        return Optional.of(vehicle);
    }

    public void removeVehicle(String vin) {
        Optional<Vehicle> vehicle = vehicleRepo.getVehicleByVin(vin);
        vehicle.ifPresent(vehicleRepo::delete);
    }

    public Optional<Vehicle> updateVehicleInformation(String vin, Vehicle vehicle) {
        Optional<Vehicle> existingVehicleOpt = vehicleRepo.getVehicleByVin(vin);
        if (existingVehicleOpt.isPresent()) {
            Vehicle existingVehicle = existingVehicleOpt.get();
            existingVehicle.setVin(vehicle.getVin());
            existingVehicle.setModel(vehicle.getModel());
            existingVehicle.setColor(vehicle.getColor());
            existingVehicle.setNew(vehicle.isNew());
            existingVehicle.setBodystyle(vehicle.getBodystyle());
            existingVehicle.setTransmission(vehicle.getTransmission());
            existingVehicle.setInterior(vehicle.getInterior());
            existingVehicle.setYear(vehicle.getYear());
            existingVehicle.setMsrp(vehicle.getMsrp());
            existingVehicle.setSaleprice(vehicle.getSaleprice());
            existingVehicle.setMileage(vehicle.getMileage());
            existingVehicle.setDescription(vehicle.getDescription());
            existingVehicle.setFeatured(vehicle.isFeatured());
            existingVehicle.setPhoto(vehicle.getPhoto());
            existingVehicle.setIssold(vehicle.isIssold());

            vehicleRepo.save(existingVehicle);
            return Optional.of(existingVehicle);
        } else {
            return Optional.empty();
        }
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

    public VehicleDTO convertToDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getVehicleid(),
                vehicle.getVin(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getBodystyle(),
                vehicle.getTransmission(),
                vehicle.getInterior(),
                vehicle.getYear(),
                vehicle.getMsrp(),
                vehicle.getMileage(),
                vehicle.getDescription(),
                vehicle.getPhoto(),
                vehicle.isFeatured(),
                vehicle.isIssold(),
                vehicle.getSaleprice(),
                vehicle.isNew(),
                vehicle.getUserEntity().getFirstname() +" "+ vehicle.getUserEntity().getLastname()
        );
    }

    public Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleid(vehicleDTO.getVehicleid());
        vehicle.setVin(vehicleDTO.getVin());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setBodystyle(vehicleDTO.getBodystyle());
        vehicle.setTransmission(vehicleDTO.getTransmission());
        vehicle.setInterior(vehicleDTO.getInterior());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setMsrp(vehicleDTO.getMsrp());
        vehicle.setMileage(vehicleDTO.getMileage());
        vehicle.setDescription(vehicleDTO.getDescription());
        vehicle.setPhoto(vehicleDTO.getPhoto());
        vehicle.setFeatured(vehicleDTO.isFeatured());
        vehicle.setIssold(vehicleDTO.isIssold());
        vehicle.setSaleprice(vehicleDTO.getSaleprice());
        vehicle.setNew(vehicleDTO.isNew());
        vehicle.setUserEntity(new UserEntity()); // You can set userEntity if needed.
        return vehicle;
    }



}
