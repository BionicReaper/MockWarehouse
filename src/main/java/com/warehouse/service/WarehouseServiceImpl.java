package com.warehouse.service;

import com.warehouse.entity.Warehouse;
import com.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link WarehouseService} that provides CRUD and business operations
 * for managing warehouses.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    /**
     * Constructs a new {@code WarehouseServiceImpl} with the given warehouse repository.
     *
     * @param warehouseRepository the repository used to access warehouse data
     */
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    /**
     * Retrieves all warehouses.
     *
     * @return a list of all warehouses
     */
    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    /**
     * Retrieves a warehouse by its ID.
     *
     * @param id the ID of the warehouse
     * @return an {@link Optional} containing the warehouse if found, or empty if not found
     */
    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    /**
     * Creates a new warehouse.
     *
     * @param warehouse the warehouse to create
     * @return the created warehouse
     */
    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    /**
     * Updates an existing warehouse identified by its ID.
     *
     * @param id        the ID of the warehouse to update
     * @param warehouse the updated warehouse data
     * @return the updated warehouse
     * @throws RuntimeException if the warehouse with the specified ID does not exist
     */
    @Override
    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        return warehouseRepository.findById(id)
                .map(existing -> {
                    existing.setName(warehouse.getName());
                    existing.setAddress(warehouse.getAddress());
                    existing.setCapacity(warehouse.getCapacity());
                    existing.setManagerName(warehouse.getManagerName());
                    return warehouseRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    /**
     * Deletes a warehouse by its ID.
     *
     * @param id the ID of the warehouse to delete
     */
    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    /**
     * Finds warehouses whose names contain the specified substring, case-insensitive.
     *
     * @param name the substring to search for in warehouse names
     * @return a list of warehouses matching the name criteria
     */
    @Override
    public List<Warehouse> findWarehousesByName(String name) {
        return warehouseRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Finds warehouses with capacity greater than the specified minimum capacity.
     *
     * @param minCapacity the minimum capacity threshold
     * @return a list of warehouses with capacity greater than the specified amount
     */
    @Override
    public List<Warehouse> findWarehousesByCapacity(BigDecimal minCapacity) {
        return warehouseRepository.findByCapacityGreaterThan(minCapacity);
    }

    /**
     * Searches for warehouses by either name or minimum capacity.
     * Only one search criteria should be provided at a time.
     *
     * @param name        the substring to search for in warehouse names (optional)
     * @param minCapacity the minimum capacity threshold (optional)
     * @return a list of warehouses matching the search criteria
     * @throws RuntimeException if both or none of the parameters are provided
     */
    @Override
    public List<Warehouse> search(String name, BigDecimal minCapacity) {
        if (name != null && minCapacity == null)
            return this.findWarehousesByName(name);
        else if (name == null && minCapacity != null)
            return this.findWarehousesByCapacity(minCapacity);
        else
            throw new RuntimeException("Bad Request");
    }
}
