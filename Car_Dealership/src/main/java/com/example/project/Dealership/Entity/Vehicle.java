package com.example.project.Dealership.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Entity
public class Vehicle {

        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Id
        @Setter
        private int vehicleid;

        @Column(nullable = false)
        @Setter
        private String vin;

        @ManyToOne
        @JoinColumn(name = "modelid")
        @Setter
        private Models model;

        @Column(nullable = false)
        @Setter
        private String color;

        @Column(nullable = false, name= "type")
        @Setter
        private boolean isNew;

        @Column(nullable = false)
        @Setter
        private String bodystyle;

        @Column(nullable = false)
        @Setter
        private String transmission;

        @Column(nullable = false)
        @Setter
        private String interior;

        @Column(nullable = false)
        @Setter
        private int year;

        @Column(nullable = false)
        @Setter
        private int msrp;

        @Column(nullable = false)
        @Setter
        private int mileage;

        @Column(nullable = false)
        @Setter
        private String description;

        @Column(nullable = false)
        @Setter
        private String photo;

        @Column(nullable = false)
        @Setter
        private boolean featured;

        @Column(nullable = false)
        @Setter
        private boolean issold;


        @Column(nullable = false)
        @Setter
        private BigDecimal saleprice;


    }
