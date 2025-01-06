package com.example.project.Dealership.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class ContactMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Message", nullable = false, length = 255)
    private String message;

    @ManyToOne
    @JoinColumn(name = "VehicleId", nullable = false)
    private Vehicle vehicle;
}
