package com.example.domily.controller;

import com.example.domily.entity.HomeService;  // Changed to HomeService
import com.example.domily.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<HomeService> createService(@RequestBody HomeService service) {  // Changed to HomeService
        return ResponseEntity.ok(serviceService.createService(service));
    }

    @GetMapping
    public ResponseEntity<List<HomeService>> getAllServices() {  // Changed to HomeService
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeService> getServiceById(@PathVariable Long id) {  // Changed to HomeService
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HomeService> updateService(@PathVariable Long id, @RequestBody HomeService service) {  // Changed to HomeService
        return ResponseEntity.ok(serviceService.updateService(id, service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<HomeService>> searchServicesByName(@RequestParam String query) {  // Changed to HomeService
        List<HomeService> matchingServices = serviceService.searchServicesByName(query);
        return ResponseEntity.ok(matchingServices);
    }

    @GetMapping("/test")
    public String test() {
        return "Service API is working";
    }
}
