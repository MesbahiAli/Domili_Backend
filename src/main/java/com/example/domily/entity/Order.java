package com.example.domily.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ordre_adresse;
    private String orderDate;
    private String status;
    private String start_hour;
    private String end_hour;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-order") // Prevent serialization of the "user" field in Order
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonBackReference("service-order")
    private HomeService service;

    // Transient fields to include clientId and serviceId in the response
    @Transient
    private Long clientId;

    @Transient
    private Long serviceId;

    // Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdre_adresse() {
        return ordre_adresse;
    }

    public void setOrdre_adresse(String ordre_adresse) {
        this.ordre_adresse = ordre_adresse;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HomeService getService() {
        return service;
    }

    public void setService(HomeService service) {
        this.service = service;
    }

    // Transient field getters and setters
    public Long getClientId() {
        return (user != null) ? user.getId() : null;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getServiceId() {
        return (service != null) ? service.getId() : null;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}