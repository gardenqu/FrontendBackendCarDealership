package com.example.project.Dealership.Repository;

import com.example.project.Dealership.Entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepo extends JpaRepository<Make, Integer> {
}
