package com.example.project.Dealership.Entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class VehicleDTO {


    private int vehicleid;
    private String vin;
    private Models model;
    private String color;
    private String bodystyle;
    private String transmission;
    private String interior;
    private int year;
    private int msrp;
    private int mileage;
    private String description;
    private String photo;
    private boolean featured;
    private boolean issold;
    private BigDecimal saleprice;
    private boolean isNew;  // Assuming 'new' is a boolean field
    private String user;

    // Constructor to populate the DTO from the Vehicle entity


    public VehicleDTO(int vehicleid, String vin, Models model, String color, String bodystyle, String transmission, String interior,
                      int year, int msrp, int mileage, String description, String photo,
                      boolean featured, boolean issold, BigDecimal saleprice, boolean isNew, String user) {
        this.vehicleid = vehicleid;
        this.vin = vin;
        this.model = model;
        this.color = color;
        this.bodystyle = bodystyle;
        this.transmission = transmission;
        this.interior = interior;
        this.year = year;
        this.msrp = msrp;
        this.mileage = mileage;
        this.description = description;
        this.photo = photo;
        this.featured = featured;
        this.issold = issold;
        this.saleprice = saleprice;
        this.isNew = isNew;
        this.user = user;
    }
}
