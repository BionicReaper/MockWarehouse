package com.warehouse.service;

import com.warehouse.dto.CreateWarehouseDTO;
import com.warehouse.dto.ResponseWarehouseDTO;
import com.warehouse.dto.UpdateWarehouseDTO;
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
    List<ResponseWarehouseDTO> getAllWarehouses();

    /**
     * Retrieves a warehouse by its ID.
     *
     * @param id the ID of the warehouse
     * @return an {@link Optional} containing the warehouse if found, or empty if not found
     */
    Optional<ResponseWarehouseDTO> getWarehouseById(Long id);

    /**
     * Creates a new warehouse.
     *
     * @param warehouseDTO the warehouse to create
     * @return the created warehouse
     */
    ResponseWarehouseDTO createWarehouse(CreateWarehouseDTO warehouseDTO);

    /**
     * Updates an existing warehouse identified by its ID.
     *
     * @param id        the ID of the warehouse to update
     * @param warehouseDTO the updated warehouse data
     * @return the updated warehouse
     */
    ResponseWarehouseDTO updateWarehouse(Long id, UpdateWarehouseDTO warehouseDTO);

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
    List<ResponseWarehouseDTO> findWarehousesByName(String name);

    /**
     * Finds warehouses with capacity greater than or equal to the specified minimum capacity.
     *
     * @param minCapacity the minimum capacity threshold
     * @return a list of warehouses with capacity greater than or equal to the specified amount
     */
    List<ResponseWarehouseDTO> findWarehousesByCapacity(BigDecimal minCapacity);

    /**
     * Searches for warehouses by optional criteria: name and minimum capacity.
     * Both criteria can be combined.
     *
     * @param name        the substring to search for in warehouse names (optional)
     * @param minCapacity the minimum capacity threshold (optional)
     * @return a list of warehouses matching the search criteria
     */
    List<ResponseWarehouseDTO> search(String name, BigDecimal minCapacity);
}
