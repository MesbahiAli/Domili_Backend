package com.example.domily.repository;

import com.example.domily.entity.HomeService;  // Changed to HomeService
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRepository extends JpaRepository<HomeService, Long> {  // Changed to HomeService

    List<HomeService> findByNomContainingIgnoreCase(String nom);
}
