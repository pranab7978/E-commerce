package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("category") String category,
            @RequestParam("gender") String gender,
            @RequestParam(value = "stock", defaultValue = "1") Integer stock,
            @RequestParam(value = "sizes", required = false) List<String> sizes,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles) {
        
        try {
            Product product = new Product();
            product.setTitle(title);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setGender(gender);
            product.setStock(stock);
            
            // 1. Handle Images
            List<String> fileNames = productService.saveImages(imageFiles); 
            product.setImages(fileNames);
            
            // 2. Handle Sizes (Split string if it comes as "S,M,L")
            if (sizes != null && sizes.size() == 1 && sizes.get(0).contains(",")) {
                sizes = Arrays.asList(sizes.get(0).split("\\s*,\\s*"));
            } else if (sizes == null) {
                sizes = new ArrayList<>();
            }
            product.setSizes(sizes);

            productService.addProduct(product);
            return ResponseEntity.ok("Product added successfully"); 
        } catch (IOException e) {
             return ResponseEntity.status(500).body("Error saving images: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        // --- DEBUG: ADD DUMMY PRODUCT IF EMPTY ---
        if (products.isEmpty()) {
            Product dummy = new Product();
            dummy.setId(999L);
            dummy.setTitle("Test Product (Auto-Generated)");
            dummy.setDescription("If you see this, the Backend is working, but your Database was empty.");
            dummy.setPrice(100.0);
            dummy.setStock(10);
            dummy.setCategory("Essentials");
            dummy.setGender("Unisex");
            dummy.setSizes(Arrays.asList("S", "M", "L"));
            // Use a placeholder image from the internet to ensure it shows
            dummy.setImages(Arrays.asList("https://via.placeholder.com/300?text=Backend+Working"));
            products.add(dummy);
        }
        // -----------------------------------------

        List<ProductDTO> dtos = products.stream().map(p -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(p.getId());
            dto.setTitle(p.getTitle());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setCategory(p.getCategory());
            dto.setGender(p.getGender());
            dto.setStock(p.getStock());
            dto.setSizes(p.getSizes());
            
            List<String> urls = new ArrayList<>();
            if(p.getImages() != null) {
                for(String img : p.getImages()) {
                    // Check if it's already a full URL (like our dummy product)
                    if (img.startsWith("http")) {
                        urls.add(img);
                    } else {
                        urls.add("http://localhost:8080/uploads/" + img); 
                    }
                }
            }
            dto.setImages(urls);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error deleting product"));
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody List<Map<String, Object>> cartItems) {
        try {
            productService.processCheckout(cartItems);
            return ResponseEntity.ok(Map.of("message", "Order confirmed! Inventory updated."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Transaction failed: " + e.getMessage()));
        }
    }
}