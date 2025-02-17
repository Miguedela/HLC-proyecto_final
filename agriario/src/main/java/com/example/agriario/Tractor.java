package com.example.agriario;

import jakarta.persistence.*;

@Entity
@Table(name = "tractores")
public class Tractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String image;
    private String description;
    private int stock;

    // Constructor vacío para JPA
    public Tractor() {
    }

    // Constructor con parámetros
    public Tractor(String name, double price, String image, String description, int stock) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.stock = stock;
    }

    // Getters y Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        this.image = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int rating) {
        this.stock = rating;
    }
}
