package com.warehouse.controller;

import com.warehouse.dto.warehouse.CreateWarehouseDTO;
import com.warehouse.dto.warehouse.ResponseWarehouseDTO;
import com.warehouse.dto.warehouse.UpdateWarehouseDTO;
import com.warehouse.entity.Warehouse;
import com.warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for handling operations related to {@link Warehouse}.
 */
@RestController
@RequestMapping("api/warehouses")
@CrossOrigin(origins = "*")
public class WarehouseController {

    private final WarehouseService warehouseService;

    /**
     * Constructs a new {@code WarehouseController} with the given {@code WarehouseService}.
     *
     * @param warehouseService the service layer for warehouse operations
     */
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * Retrieves a list of all warehouses.
     *
     * @return a {@code ResponseEntity} containing the list of all warehouses and HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<ResponseWarehouseDTO>> getAllWarehouses() {
        List<ResponseWarehouseDTO> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    /**
     * Retrieves a warehouse by its ID.
     *
     * @param id the ID of the warehouse to retrieve
     * @return a {@code ResponseEntity} containing the warehouse and HTTP 200 OK if found,
     *         or HTTP 404 Not Found if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new warehouse.
     *
     * @param warehouseDTO the warehouse object to create
     * @return a {@code ResponseEntity} containing the created warehouse and HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<ResponseWarehouseDTO> createWarehouse(@RequestBody CreateWarehouseDTO warehouseDTO) {
        ResponseWarehouseDTO created = warehouseService.createWarehouse(warehouseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing warehouse by ID.
     *
     * @param id        the ID of the warehouse to update
     * @param warehouse the updated warehouse object
     * @return a {@code ResponseEntity} containing the updated warehouse and HTTP 200 OK,
     *         or HTTP 404 Not Found if the warehouse does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseWarehouseDTO> updateWarehouse(
            @PathVariable Long id,
            @RequestBody UpdateWarehouseDTO warehouse) {
        try {
            ResponseWarehouseDTO updated = warehouseService.updateWarehouse(id, warehouse);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a warehouse by its ID.
     *
     * @param id the ID of the warehouse to delete
     * @return HTTP 204 No Content if successfully deleted, or HTTP 404 Not Found if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable Long id) {
        try {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for warehouses by optional name and/or minimum capacity.
     *
     * @param name         the name (or part of the name) to filter warehouses
     * @param minCapacity  the minimum storage capacity required
     * @return a {@code ResponseEntity} with the list of matching warehouses and HTTP 200 OK,
     *         or HTTP 400 Bad Request if parameters are invalid
     */
    @GetMapping("/search")
    public ResponseEntity<List<ResponseWarehouseDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minCapacity) {
        List<ResponseWarehouseDTO> found;
        try {
            found = warehouseService.search(name, minCapacity);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(found);
    }

}
