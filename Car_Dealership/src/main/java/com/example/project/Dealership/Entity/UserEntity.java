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
    @Setter
    private String firstname;
    @Column
    @Setter
    private String lastname;
    @Column
    @Setter
    private String email;
    @Column
    @Setter
    private String password;
    @Column
    @Setter
    private String role;




    /*UserId Serial PRIMARY KEY,

FirstName varchar(40) NOT NULL,

LastName varchar(40) NOT NULL,

Email varchar(100) NOT NULL UNIQUE,

Password varchar(100) NOT NULL,

Role varchar(20) NOT NULL*/


}
