package com.example.apelsin.controller;

import com.example.apelsin.dto.ProductDTO;
import com.example.apelsin.repository.ProductRepository;
import com.example.apelsin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {
    final ProductRepository productRepository;
    final ProductService productService;

    @GetMapping("/high_demand_products")
    public HttpEntity<?> high_demand_products(){
        List<ProductDTO> productByCount = productRepository.getProductByCount();
        return ResponseEntity.ok().body(productByCount);
    }
}
