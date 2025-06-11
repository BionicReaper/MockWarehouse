package com.warehouse.service;

import com.warehouse.entity.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link Warehouse} entities.
 * Defines CRUD operations and business-specific queries related to warehouses.
 */
public interface WarehouseService {

    /**
     * Retrieves all warehouses.
     *
     * @return a list of all warehouses
     */
    List<Warehouse> getAllWarehouses();

    /**
     * Retrieves a warehouse by its ID.
     *
     * @param id the ID of the warehouse
     * @return an {@link Optional} containing the warehouse if found, or empty if not found
     */
    Optional<Warehouse> getWarehouseById(Long id);

    /**
     * Creates a new warehouse.
     *
     * @param warehouse the warehouse to create
     * @return the created warehouse
     */
    Warehouse createWarehouse(Warehouse warehouse);

    /**
     * Updates an existing warehouse identified by its ID.
     *
     * @param id        the ID of the warehouse to update
     * @param warehouse the updated warehouse data
     * @return the updated warehouse
     */
    Warehouse updateWarehouse(Long id, Warehouse warehouse);

    /**
     * Deletes a warehouse by its ID.
     *
     * @param id the ID of the warehouse to delete
     */
    void deleteWarehouse(Long id);

    /**
     * Finds warehouses whose names contain the specified substring, case-insensitive.
     *
     * @param name the substring to search for in warehouse names
     * @return a list of warehouses matching the name criteria
     */
    List<Warehouse> findWarehousesByName(String name);

    /**
     * Finds warehouses with capacity greater than or equal to the specified minimum capacity.
     *
     * @param minCapacity the minimum capacity threshold
     * @return a list of warehouses with capacity greater than or equal to the specified amount
     */
    List<Warehouse> findWarehousesByCapacity(BigDecimal minCapacity);

    /**
     * Searches for warehouses by optional criteria: name and minimum capacity.
     * Both criteria can be combined.
     *
     * @param name        the substring to search for in warehouse names (optional)
     * @param minCapacity the minimum capacity threshold (optional)
     * @return a list of warehouses matching the search criteria
     */
    List<Warehouse> search(String name, BigDecimal minCapacity);
}
