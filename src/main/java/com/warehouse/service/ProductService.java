package com.warehouse.service;

import com.warehouse.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    //CRUD operations
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Long id, Product product);
    public void deleteProduct(Long id);

    //Business operations
    public List<Product> findProductsByCategory(String category);
    public List<Product> findProductsByName(String name);
    public List<Product> findProductsByPriceBetween(BigDecimal low, BigDecimal high);
    public List<String> findAllCategories();

}
