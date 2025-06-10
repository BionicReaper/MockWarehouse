package com.warehouse.controller;

import com.warehouse.entity.Product;
import com.warehouse.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // GET api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET api/products/search
    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<Product> products;
        try {
            products = productService.search(category, name, minPrice, maxPrice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(products);
    }

    // GET api/products/categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = productService.findAllCategories();
        return ResponseEntity.ok(categories);
    }
}
