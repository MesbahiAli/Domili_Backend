package com.example.domily.entity;

public class OrderRequest {
    private String ordre_adresse;
    private String orderDate;
    private String status;
    private String start_hour;
    private String end_hour;
    private Long clientId; // ID of the client (User)
    private Long serviceId; // ID of the HomeService

    // Getters and setters
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}