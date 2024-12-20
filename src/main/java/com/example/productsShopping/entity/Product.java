package com.example.productsShopping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product marka is required")
    @Size(min = 1, max = 5000, message = "Brad must be between 1 and 5000 characters")
    private  String brad;

    @NotBlank(message = "Product marka is required")
    @Size(min = 1, max = 5000, message = "Mark must be between 1 and 5000 characters")
    private String model;

    @NotBlank(message = "Product marka is required")
    @Size(min = 1, max = 5000, message = "Category must be between 1 and 5000 characters")
    private  String category;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "Description cannot be null")
    @Size(min = 1, max = 5000, message = "Description must be between 1 and 5000 characters")
    private  String description;


    @NotNull(message = "Rate cannot be null")
    @DecimalMin(value = "0", message = "Price must be at last 0")
    @DecimalMax(value = "5", message = "Rate must be lase than or equal to 5")
    private int rate;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "Image url cannot be null")
    @Size(min = 1, max = 500, message = "Image url must be between 1 and 5000 characters")
    private String imageurl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private  User user;
}
