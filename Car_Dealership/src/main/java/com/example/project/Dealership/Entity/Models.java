package com.example.project.Dealership.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Entity(name="model")
public class Models {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Setter
    private int modelid;


    @Column(nullable = false)
    @Setter
    private String modelname;

    @ManyToOne
    @JoinColumn(name = "makeid")
    private Make make;






}
