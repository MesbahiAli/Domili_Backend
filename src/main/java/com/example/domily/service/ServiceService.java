package com.example.domily.service;

import com.example.domily.entity.HomeService;  // Changed to HomeService
import com.example.domily.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public HomeService createService(HomeService service) {  // Changed to HomeService
        return serviceRepository.save(service);
    }

    public List<HomeService> getAllServices() {  // Changed to HomeService
        return serviceRepository.findAll();
    }

    public HomeService getServiceById(Long id) {  // Changed to HomeService
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
    }

    public HomeService updateService(Long id, HomeService updatedService) {  // Changed to HomeService
        HomeService existingService = getServiceById(id);
        existingService.setNom(updatedService.getNom());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        existingService.setAvailability(updatedService.isAvailability());
        return serviceRepository.save(existingService);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    // Search services by name
    public List<HomeService> searchServicesByName(String query) {  // Changed to HomeService
        return serviceRepository.findByNomContainingIgnoreCase(query);
    }
}
