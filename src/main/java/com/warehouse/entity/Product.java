package com.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents a product stored in the warehouse system.
 * A product has basic attributes such as name, description, price, category, and weight.
 */
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends BaseEntity {

    /**
     * The name of the product.
     * This field is required and limited to 100 characters.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * A detailed description of the product.
     * This field is required and limited to 512 characters.
     */
    @Column(name = "description", nullable = false, length = 512)
    private String description;

    /**
     * The price of the product.
     * This field is required and supports up to 10 digits with 2 decimal places.
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The category to which the product belongs.
     * This field is required and limited to 100 characters.
     */
    @Column(name = "category", nullable = false, length = 100)
    private String category;

    /**
     * The weight of the product.
     * This field is required and supports up to 10 digits with 2 decimal places.
     */
    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;
}
