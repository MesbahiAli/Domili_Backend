package com.example.domily.service;

import com.example.domily.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public com.example.domily.entity.Service createService(com.example.domily.entity.Service service) {
        return serviceRepository.save(service);
    }

    public List<com.example.domily.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public com.example.domily.entity.Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
    }

    public com.example.domily.entity.Service updateService(Long id, com.example.domily.entity.Service updatedService) {
        com.example.domily.entity.Service existingService = getServiceById(id);
        existingService.setNom(updatedService.getNom());
        existingService.setPrenom(updatedService.getPrenom());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        existingService.setAvailability(updatedService.isAvailability());
        return serviceRepository.save(existingService);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
