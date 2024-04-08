package com.example.project.Dealership.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Purchase {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    @Setter
    private int purchaseid;
    @Column
    @Setter
    private String name;
    @Column
    @Setter
    private String phone;
    @Column
    @Setter
    private String email;
    @Column
    @Setter
    private String street1;
    @Column
    @Setter
    private String street2;
    @Column
    @Setter
    private String state;
    @Column
    @Setter
    private String city;
    @Column
    @Setter
    private String zipcode;
    @Column
    @Setter
    private String purchasedate;
    @Column
    @Setter
    private String purchastype;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

    @Setter
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
