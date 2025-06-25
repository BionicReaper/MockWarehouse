package com.warehouse.service;

import com.warehouse.dto.product.CreateProductDTO;
import com.warehouse.dto.product.ResponseProductDTO;
import com.warehouse.dto.product.UpdateProductDTO;
import com.warehouse.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link Product} entities.
 * Defines CRUD and business-specific operations related to products.
 */
public interface ProductService {

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    List<ResponseProductDTO> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return an Optional containing the found product or empty if not found
     */
    Optional<ResponseProductDTO> getProductById(Long id);

    /**
     * Creates a new product.
     *
     * @param productDTO the product entity to create
     * @return the created product
     */
    ResponseProductDTO createProduct(CreateProductDTO productDTO);

    /**
     * Updates an existing product by ID.
     *
     * @param id         the ID of the product to update
     * @param productDTO the product data to update
     * @return the updated product
     */
    ResponseProductDTO updateProduct(Long id, UpdateProductDTO productDTO);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    void deleteProduct(Long id);

    /**
     * Finds products by category.
     *
     * @param category the category name to filter products by
     * @return a list of products in the given category
     */
    List<ResponseProductDTO> findProductsByCategory(String category);

    /**
     * Finds products whose names contain the specified string (case-insensitive).
     *
     * @param name the substring to search for within product names
     * @return a list of matching products
     */
    List<ResponseProductDTO> findProductsByName(String name);

    /**
     * Finds products with prices within the specified range (inclusive).
     *
     * @param low  the lower bound of the price range
     * @param high the upper bound of the price range
     * @return a list of products priced between low and high
     */
    List<ResponseProductDTO> findProductsByPriceBetween(BigDecimal low, BigDecimal high);

    /**
     * Retrieves all distinct product categories available.
     *
     * @return a list of unique category names
     */
    List<String> findAllCategories();

    /**
     * Searches for products using multiple optional filters.
     *
     * @param category the category name to filter by (nullable)
     * @param name     the name substring to search for (nullable)
     * @param minPrice the minimum price to filter by (nullable)
     * @param maxPrice the maximum price to filter by (nullable)
     * @return a list of products matching the given criteria
     */
    List<ResponseProductDTO> search(String category, String name, BigDecimal minPrice, BigDecimal maxPrice);
}
