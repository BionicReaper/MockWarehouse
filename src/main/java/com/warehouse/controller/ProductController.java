package com.warehouse.controller;

import com.warehouse.entity.Product;
import com.warehouse.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing products in the warehouse.
 */
@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new ProductController with the given ProductService.
     *
     * @param productService the service to manage product operations
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id the ID of the product
     * @return the product if found, or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new product.
     *
     * @param product the product to be created
     * @return the created product with 201 Created status
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id      the ID of the product to update
     * @param product the updated product details
     * @return the updated product or 404 Not Found if it doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the ID of the product to delete
     * @return 204 No Content if successful, or 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for products based on optional filters like category, name, minimum price, and maximum price.
     *
     * @param category the product category to filter by (optional)
     * @param name     the product name to filter by (optional)
     * @param minPrice the minimum price to filter by (optional)
     * @param maxPrice the maximum price to filter by (optional)
     * @return a list of products matching the search criteria
     */
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

    /**
     * Retrieves all unique product categories.
     *
     * @return a list of category names
     */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = productService.findAllCategories();
        return ResponseEntity.ok(categories);
    }
}
