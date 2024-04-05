package com.example.project.Dealership.Entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class User {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Setter
    private int userid;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;




    /*UserId Serial PRIMARY KEY,

FirstName varchar(40) NOT NULL,

LastName varchar(40) NOT NULL,

Email varchar(100) NOT NULL UNIQUE,

Password varchar(100) NOT NULL,

Role varchar(20) NOT NULL*/


}
