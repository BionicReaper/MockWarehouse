package com.warehouse.service;

import com.warehouse.dto.warehouse.CreateWarehouseDTO;
import com.warehouse.dto.warehouse.ResponseWarehouseDTO;
import com.warehouse.dto.warehouse.UpdateWarehouseDTO;
import com.warehouse.dto.mapper.WarehouseMapper;
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
    private final WarehouseMapper mapper;

    /**
     * Constructs a new {@code WarehouseServiceImpl} with the given warehouse repository.
     *
     * @param warehouseRepository the repository used to access warehouse data
     */
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.mapper = warehouseMapper;
    }

    /**
     * Retrieves all warehouses.
     *
     * @return a list of all warehouses
     */
    @Override
    public List<ResponseWarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return mapper.toResponseDto(warehouses);
    }

    /**
     * Retrieves a warehouse by its ID.
     *
     * @param id the ID of the warehouse
     * @return an {@link Optional} containing the warehouse if found, or empty if not found
     */
    @Override
    public Optional<ResponseWarehouseDTO> getWarehouseById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        Optional<ResponseWarehouseDTO> responseWarehouseDTO;
        responseWarehouseDTO = warehouse.map(mapper::toResponseDto);
        return responseWarehouseDTO;
    }

    /**
     * Creates a new warehouse.
     *
     * @param warehouseDTO the warehouse to create
     * @return the created warehouse
     */
    @Override
    public ResponseWarehouseDTO createWarehouse(CreateWarehouseDTO warehouseDTO) {
        Warehouse warehouse = mapper.toEntity(warehouseDTO);
        Warehouse saved = warehouseRepository.save(warehouse);
        return mapper.toResponseDto(saved);
    }

    /**
     * Updates an existing warehouse identified by its ID.
     *
     * @param id        the ID of the warehouse to update
     * @param warehouseDTO the updated warehouse data
     * @return the updated warehouse
     * @throws RuntimeException if the warehouse with the specified ID does not exist
     */
    @Override
    public ResponseWarehouseDTO updateWarehouse(Long id, UpdateWarehouseDTO warehouseDTO) {
        Warehouse existing = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
        Warehouse warehouse = mapper.toEntity(warehouseDTO);

        existing.setName(warehouse.getName());
        existing.setAddress(warehouse.getAddress());
        existing.setCapacity(warehouse.getCapacity());
        existing.setManagerName(warehouse.getManagerName());

        Warehouse saved = warehouseRepository.save(existing);
        return mapper.toResponseDto(saved);
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
    public List<ResponseWarehouseDTO> findWarehousesByName(String name) {
        List<Warehouse> warehouses = warehouseRepository.findByNameContainingIgnoreCase(name);
        return mapper.toResponseDto(warehouses);
    }

    /**
     * Finds warehouses with capacity greater than the specified minimum capacity.
     *
     * @param minCapacity the minimum capacity threshold
     * @return a list of warehouses with capacity greater than the specified amount
     */
    @Override
    public List<ResponseWarehouseDTO> findWarehousesByCapacity(BigDecimal minCapacity) {
        List<Warehouse> warehouses = warehouseRepository.findByCapacityGreaterThan(minCapacity);
        return mapper.toResponseDto(warehouses);
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
    public List<ResponseWarehouseDTO> search(String name, BigDecimal minCapacity) {
        if (name != null && minCapacity == null)
            return this.findWarehousesByName(name);
        else if (name == null && minCapacity != null)
            return this.findWarehousesByCapacity(minCapacity);
        else
            throw new RuntimeException("Bad Request");
    }
}
