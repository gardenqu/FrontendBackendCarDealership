package com.example.project.Dealership.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "userentity")
public class UserEntity {

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


    // One-to-many relationship with Vehicle
    @OneToMany(mappedBy = "userEntity")
    @JsonIgnore
    private List<Vehicle> vehicles;




    /*UserId Serial PRIMARY KEY,

FirstName varchar(40) NOT NULL,

LastName varchar(40) NOT NULL,

Email varchar(100) NOT NULL UNIQUE,

Password varchar(100) NOT NULL,

Role varchar(20) NOT NULL*/


}
