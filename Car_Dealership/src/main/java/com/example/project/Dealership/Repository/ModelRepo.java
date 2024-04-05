package com.example.project.Dealership.Repository;

import com.example.project.Dealership.Entity.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepo extends JpaRepository<Models, Integer> {
}
