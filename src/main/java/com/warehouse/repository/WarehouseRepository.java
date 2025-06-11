package com.warehouse.repository;

import com.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for {@link Warehouse} entities.
 * Provides CRUD operations and custom queries related to warehouses.
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    /**
     * Retrieves all warehouses with their inventories and associated products eagerly fetched.
     *
     * @return a list of all warehouses with inventories and products
     */
    @Override
    @Query("SELECT w FROM Warehouse w LEFT JOIN FETCH w.inventories i LEFT JOIN FETCH i.product")
    List<Warehouse> findAll();

    /**
     * Finds warehouses whose names contain the specified substring, case-insensitive.
     *
     * @param name the substring to search for in warehouse names
     * @return a list of warehouses matching the name criteria
     */
    List<Warehouse> findByNameContainingIgnoreCase(String name);

    /**
     * Finds warehouses with capacity greater than the specified value.
     *
     * @param capacity the capacity threshold
     * @return a list of warehouses with capacity greater than the specified amount
     */
    List<Warehouse> findByCapacityGreaterThan(BigDecimal capacity);

    /**
     * Finds warehouses that have a manager assigned (managerName is not null).
     *
     * @return a list of warehouses with assigned managers
     */
    @Query("SELECT w FROM Warehouse w WHERE w.managerName IS NOT NULL")
    List<Warehouse> findWarehousesWithManager();

}
