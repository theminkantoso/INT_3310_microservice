package com.programming.techie.productservice.controller;

import com.programming.techie.productservice.model.Product;
import com.programming.techie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll() {
        log.info("All products");
        return productRepository.findAll();
    }

    @GetMapping("/{proId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> findById(@PathVariable("proId") String proId) {
        log.info("Info product {}", proId);
        Optional<Product> output = productRepository.findById(proId);
        log.info(output.toString());
        return productRepository.findById(proId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        try {
            log.info("Creating: "+ product.toString());
            productRepository.save(product);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    @DeleteMapping("/{proId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("proId") String proId) {
        try {
            productRepository.deleteById(proId);
            log.info("Deleting: " + proId);
        } catch (Exception e) {
            log.error("Error delete " + proId);
        }
    }
}
