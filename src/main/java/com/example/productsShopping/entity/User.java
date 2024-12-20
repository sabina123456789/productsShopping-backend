package com.example.productsShopping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is requried")
    @Size(min = 1, max=50)
    private String name;


    @NotBlank(message = "Name is requried")
    @Size(min = 1, max=50)
    private String surname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Name is requried")
    @Column(unique = true)
    private  String email;

    @NotBlank(message = "Username is requred")
    @Size(min = 1, max=50)
    @Column(unique = true)
    private String username;


    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true )
    private List<CartIteam> cartItems = new ArrayList<>();





}

