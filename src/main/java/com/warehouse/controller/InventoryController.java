package com.warehouse.controller;

import com.warehouse.entity.Inventory;
import com.warehouse.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing inventory operations in the warehouse system.
 */
@RestController
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Constructs a new InventoryController with the specified InventoryService.
     *
     * @param inventoryService the inventory service to be used by this controller
     */
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves all inventory items.
     *
     * @return a list of all inventory items with HTTP 200 OK
     */
    @GetMapping("/api/inventories")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    /**
     * Retrieves an inventory item by its ID.
     *
     * @param id the ID of the inventory item
     * @return the inventory item with HTTP 200 OK if found, or HTTP 404 Not Found
     */
    @GetMapping("/api/inventories/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new inventory item.
     *
     * @param inventory the inventory item to be created
     * @return the created inventory item with HTTP 201 Created
     */
    @PostMapping("/api/inventories")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory created = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing inventory item by its ID.
     *
     * @param id        the ID of the inventory item to update
     * @param inventory the updated inventory data
     * @return the updated inventory item with HTTP 200 OK, or HTTP 404 Not Found if not found
     */
    @PutMapping("/api/inventories/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        try {
            Inventory updated = inventoryService.updateInventory(id, inventory);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an inventory item by its ID.
     *
     * @param id the ID of the inventory item to delete
     * @return HTTP 204 No Content if deletion is successful, or HTTP 404 Not Found if not found
     */
    @DeleteMapping("/api/inventories/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable Long id) {
        try {
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves all inventory items in a specific warehouse.
     *
     * @param warehouseId the ID of the warehouse
     * @return a list of inventory items in the specified warehouse with HTTP 200 OK
     */
    @GetMapping("/api/warehouses/{warehouseId}/inventory")
    public ResponseEntity<List<Inventory>> getInventoryByWarehouseId(@PathVariable Long warehouseId) {
        List<Inventory> inventories = inventoryService.findWarehouseInventory(warehouseId);
        return ResponseEntity.ok(inventories);
    }

    /**
     * Retrieves all inventory entries for a specific product.
     *
     * @param productId the ID of the product
     * @return a list of inventory entries containing the specified product with HTTP 200 OK
     */
    @GetMapping("api/inventories/product")
    public ResponseEntity<List<Inventory>> getProductInInventory(@RequestParam(name = "id", required = true) Long productId) {
        List<Inventory> inventories = inventoryService.findProductInInventory(productId);
        return ResponseEntity.ok(inventories);
    }

    /**
     * Retrieves inventory entries for a specific product within a specific warehouse.
     *
     * @param warehouseId the ID of the warehouse
     * @param productId   the ID of the product
     * @return a list of inventory entries matching the warehouse and product with HTTP 200 OK
     */
    @GetMapping("api/warehouses/{warehouseId}/inventory/product")
    public ResponseEntity<List<Inventory>> getProductInWarehouseInventory(
            @PathVariable Long warehouseId,
            @RequestParam(name = "id", required = true) Long productId) {
        List<Inventory> inventories = inventoryService.findProductInWarehouseInventory(productId, warehouseId);
        return ResponseEntity.ok(inventories);
    }

    /**
     * Retrieves inventory items that are considered low in stock.
     *
     * @return a list of low stock inventory items with HTTP 200 OK
     */
    @GetMapping("api/inventories/lowstock")
    public ResponseEntity<List<Inventory>> getLowStockInventory() {
        List<Inventory> inventories = inventoryService.findLowStockInventory();
        return ResponseEntity.ok(inventories);
    }
}
