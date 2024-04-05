package com.example.project.Dealership.Entity;

import jakarta.persistence.*;

@Entity
public class Purchase {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int purchaseid;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String street1;
    @Column
    private String street2;
    @Column
    private String state;
    @Column
    private String city;
    @Column
    private String zipcode;
    @Column
    private String purchasedate;
    @Column
    private String purchastype;
    @Column
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

    @Column
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleid")
    private Vehicle vehicle;



    //add a vehicle to the purchase


    /*
PurchaseId Serial PRIMARY KEY,

Name varchar(50) NOT NULL,

Phone varchar(20),

Email varchar(100),

Street1 varchar(100) NOT NULL,

Street2 varchar(100),

State CHAR(2) NOT NULL,

City varchar(20) NOT NULL,

ZipCode CHAR(5) NOT NULL,

PurchaseDate DATE NOT NULL,

PurchaseType varchar(20) NOT NULL,

UserId INT NOT NULL,

VehicleId INT NOT NULL,

PurchasePrice INT NOT NULL,*/


}
