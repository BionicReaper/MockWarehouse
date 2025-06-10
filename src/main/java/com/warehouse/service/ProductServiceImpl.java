package com.warehouse.service;

import com.warehouse.entity.Product;
import com.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //CRUD operations
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product){
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setCategory(product.getCategory());
                    existing.setWeight(product.getWeight());
                    return productRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    //Business operations
    @Override
    public List<Product> findProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findProductsByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> findProductsByPriceBetween(BigDecimal low, BigDecimal high){
        return productRepository.findByPriceBetween(low, high);
    }

    @Override
    public List<Product> search(String category, String name, BigDecimal minPrice, BigDecimal maxPrice){
        if(category != null && name == null && minPrice == null && maxPrice == null)
            return this.findProductsByCategory(category);
        else if(category == null && name != null && minPrice == null && maxPrice == null)
            return this.findProductsByName(name);
        else if(category == null && name == null && minPrice != null && maxPrice != null)
            return this.findProductsByPriceBetween(minPrice, maxPrice);
        else
            throw new RuntimeException("Bad Request");
    }

    @Override
    public List<String> findAllCategories(){
        return productRepository.getAllCategories();
    }

}
