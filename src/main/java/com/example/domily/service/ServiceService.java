package com.example.domily.service;
import com.example.domily.entity.HomeService;  // Changed to HomeService
import com.example.domily.repository.CategoryRepository;
import com.example.domily.repository.ServiceRepository;
import com.example.domily.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;



@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ServiceService(ServiceRepository serviceRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public HomeService createService(HomeService service) {
        // Fetch and set Category
        if (service.getCategory_id() != null) {
            com.example.domily.entity.Category category = categoryRepository.findById(service.getCategory_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            service.setCategory(category);
        }

        // Fetch and set Provider
        if (service.getProvider_id() != null) {
            com.example.domily.entity.User provider = userRepository.findById(service.getProvider_id())
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));
            service.setProvider(provider);
        }

        return serviceRepository.save(service);
    }

    public List<HomeService> getAllServices() {
        return serviceRepository.findAll();
    }

    public HomeService getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
    }

    public List<HomeService> getServicesByCategory(Long categoryId) {
        return serviceRepository.findByCategoryId(categoryId);
    }

    public HomeService updateService(Long id, HomeService updatedService) {
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
