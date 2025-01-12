package com.example.domily.service;
import com.example.domily.entity.HomeService;  // Changed to HomeService
import com.example.domily.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.Collections;
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

    public List<HomeService> getServicesByCategory(Long categoryId) {
        return serviceRepository.findByCategoryId(categoryId);
    }

    public HomeService updateService(Long id, HomeService updatedService) {  // Changed to HomeService
        HomeService existingService = getServiceById(id);
        existingService.setName(updatedService.getName());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        existingService.setAvailability(updatedService.isAvailability());
        return serviceRepository.save(existingService);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    // Search services by name
    public List<HomeService> searchServicesByName(String query) {  
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the query is blank
        }
        return serviceRepository.findByNameContainingIgnoreCase(query);
    }
    
   
}
