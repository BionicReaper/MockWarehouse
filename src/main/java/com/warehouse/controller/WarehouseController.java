package com.warehouse.controller;

import com.warehouse.entity.Warehouse;
import com.warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/warehouses")
@CrossOrigin(origins = "*")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // GET /api/warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    // GET /api/warehouses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(warehouse -> ResponseEntity.ok(warehouse))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/warehouses
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/warehouses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        try{
            Warehouse updated = warehouseService.updateWarehouse(id, warehouse);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/warehouses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable Long id) {
        try {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/warehouses/search
    @GetMapping("/search")
    public ResponseEntity<List<Warehouse>> search(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) BigDecimal minCapacity) {
        List<Warehouse> found;
        if(name != null && minCapacity == null)
            found = warehouseService.findWarehousesByName(name);
        else if(name == null && minCapacity != null)
            found = warehouseService.findWarehousesByCapacity(minCapacity);
        else
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(found);
    }

}
