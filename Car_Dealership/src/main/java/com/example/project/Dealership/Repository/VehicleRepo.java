package com.example.project.Dealership.Repository;

import com.example.project.Dealership.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {


   /* @Query(value = "SELECT * FROM Vehicle v"
            + " Inner join Make ma on v.MakeId = mo.MakeId"
            + " Inner join Model mo on ma.ModelId = mo.ModelId"
            + " WHERE"
            + " Type LIKE ?1"
            + " AND mo.Makename = ?2"
            + " AND isSold = ?3"
            + " AND salesprice < ?4;", nativeQuery = true)

    List<Vehicle> vehicleSearchBy(String type, String makename, boolean isSold,int salesPrice);*/


    @Query(value = "SELECT * FROM Vehicle v"
            + " WHERE vin = ?1", nativeQuery = true)
    Vehicle getVehicleByVin(String vin);


}
