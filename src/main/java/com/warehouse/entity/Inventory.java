package com.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an inventory record for a specific product in a warehouse.
 * Each inventory entry tracks stock levels, including quantity, minimum stock, and maximum stock.
 */
@Entity
@Getter
@Setter
@Table(name = "inventory")
public class Inventory extends BaseEntity {

    /**
     * The current quantity of the product in stock.
     * This value cannot be null.
     */
    @Column(name = "quantity", nullable = false)
    private int quantity;

    /**
     * The minimum stock level for the product.
     * Alerts or replenishment may be triggered if the quantity falls below this level.
     * This value cannot be null.
     */
    @Column(name = "min_stock", nullable = false)
    private int minStock;

    /**
     * The maximum stock level allowed for the product.
     * This helps avoid overstocking.
     * This value cannot be null.
     */
    @Column(name = "max_stock", nullable = false)
    private int maxStock;

    /**
     * The warehouse where this inventory record is located.
     * This is a many-to-one relationship, as multiple inventory records can exist in one warehouse.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    /**
     * The product associated with this inventory record.
     * This is a many-to-one relationship, as one product can be found in multiple inventory records.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
