package com.example.project.Dealership.Repository;

import com.example.project.Dealership.Entity.ContactMessages;
import com.example.project.Dealership.Entity.UserEntity;
import com.example.project.Dealership.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ContactMessageRepo  extends JpaRepository<ContactMessages, Integer> {
    List<ContactMessages> findByUserEntity(UserEntity userEntity);
    List<ContactMessages> findByVehicle(Vehicle vehicle);

}
