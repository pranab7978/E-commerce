package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1. Get the absolute path to the "uploads" folder on your hard drive
        String uploadPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        
        // 2. Tell Spring Boot: "When a user asks for /uploads/**, look in this folder"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
                
        System.out.println("Serving images from: " + uploadPath); // Check your console for this log!
    }
}