package com.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a warehouse entity containing details such as name, address,
 * capacity, manager, and associated inventory.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Table(name = "warehouse")
public class Warehouse extends BaseEntity {

    /**
     * The name of the warehouse.
     * Must not be null and is limited to 100 characters.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * The address of the warehouse.
     * Must not be null and is limited to 100 characters.
     */
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    /**
     * The storage capacity of the warehouse.
     * Stored as a decimal with precision and scale.
     */
    @Column(name = "capacity", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacity;

    /**
     * The full name of the warehouse manager.
     * Must not be null and is limited to 100 characters.
     */
    @Column(name = "manager_name", nullable = false, length = 100)
    private String managerName;

    /**
     * The list of inventory items stored in the warehouse.
     * This is a one-to-many relationship where the warehouse is the parent.
     */
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Inventory> inventories;
}
