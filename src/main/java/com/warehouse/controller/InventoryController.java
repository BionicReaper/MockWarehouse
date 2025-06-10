package com.warehouse.controller;

import com.warehouse.entity.Inventory;
import com.warehouse.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // GET /api/inventories
    @GetMapping("/api/inventories")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    // GET /api/inventories/{id}
    @GetMapping("/api/inventories/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id)
                .map(inventory -> ResponseEntity.ok(inventory))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/inventories
    @PostMapping("/api/inventories")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory created = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/inventories/{id}
    @PutMapping("/api/inventories/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        try{
            Inventory updated = inventoryService.updateInventory(id, inventory);
            return ResponseEntity.ok(updated);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/inventories/{id}
    @DeleteMapping("/api/inventories/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable Long id) {
        try {
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/warehouses/{warehouseId}/inventories/
    @GetMapping("/api/warehouses/{warehouseId}/inventory")
    public ResponseEntity<List<Inventory>> getInventoryByWarehouseId(@PathVariable Long warehouseId) {
        List<Inventory> inventories = inventoryService.findWarehouseInventory(warehouseId);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("api/inventories/product")
    public ResponseEntity<List<Inventory>> getProductInInventory(@RequestParam(name = "id", required = true) Long productId) {
        List<Inventory> inventories = inventoryService.findProductInInventory(productId);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("api/warehouses/{warehouseId}/inventory/product")
    public ResponseEntity<List<Inventory>> getProductInWarehouseInventory(
            @PathVariable Long warehouseId,
            @RequestParam(name = "id", required = true) Long productId) {
        List<Inventory> inventories = inventoryService.findProductInWarehouseInventory(productId, warehouseId);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("api/inventories/lowstock")
    public ResponseEntity<List<Inventory>> getLowStockInventory() {
        List<Inventory> inventories = inventoryService.findLowStockInventory();
        return ResponseEntity.ok(inventories);
    }
}
