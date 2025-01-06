package com.example.project.Dealership.Repository;

import com.example.project.Dealership.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);


}
