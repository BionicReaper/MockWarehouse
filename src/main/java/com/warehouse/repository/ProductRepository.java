package com.warehouse.repository;

import com.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for {@link Product} entities.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all products belonging to the specified category.
     *
     * @param category the category name to filter products by
     * @return a list of products in the given category
     */
    List<Product> findByCategory(String category);

    /**
     * Finds products whose names contain the specified string (case-insensitive).
     *
     * @param name the substring to search for within product names
     * @return a list of matching products
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Finds products with prices within the specified range (inclusive).
     *
     * @param low  the lower bound of the price range
     * @param high the upper bound of the price range
     * @return a list of products priced between low and high
     */
    List<Product> findByPriceBetween(BigDecimal low, BigDecimal high);

    /**
     * Retrieves all distinct product categories available.
     *
     * @return a list of unique category names
     */
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> getAllCategories();
}
