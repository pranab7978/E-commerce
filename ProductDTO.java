package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String gender;
    private Integer stock;
    private List<String> sizes = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public List<String> getSizes() { return sizes; }
    public void setSizes(List<String> sizes) { this.sizes = sizes; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}