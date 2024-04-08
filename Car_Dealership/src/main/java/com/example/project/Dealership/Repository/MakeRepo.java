package com.example.project.Dealership.Repository;

import com.example.project.Dealership.Entity.Make;
import com.example.project.Dealership.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepo extends JpaRepository<Make, Integer> {

}
