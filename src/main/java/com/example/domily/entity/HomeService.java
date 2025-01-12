package com.example.domily.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "services")
public class HomeService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 
    private String description;
    private BigDecimal price; 
    private boolean availability;

@ManyToOne
@JoinColumn(name = "category_id")
@JsonBackReference("service-category") // Prevent serialization of "category" field in HomeService
private Category category;

@ManyToOne
@JoinColumn(name = "provider_id")
@JsonBackReference("service-provider") // Prevent serialization of "provider" (User) field in HomeService
private User provider;

@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
@JsonManagedReference("service-review") // Prevent infinite recursion in Review
private List<Review> reviews;

@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
@JsonManagedReference("service-order") // Prevent infinite recursion in Order
@JsonIgnoreProperties("service") // Prevent serialization of the "service" field in Order
private List<Order> orders;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public boolean isAvailability() {
        return availability;
    }


    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public User getProvider() {
        return provider;
    }


    public void setProvider(User provider) {
        this.provider = provider;
    }


    public List<Review> getReviews() {
        return reviews;
    }


    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    public List<Order> getOrders() {
        return orders;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    

}