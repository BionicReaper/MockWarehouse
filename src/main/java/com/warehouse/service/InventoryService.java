package com.warehouse.service;

import com.warehouse.entity.Inventory;
import com.warehouse.entity.Product;
import com.warehouse.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    //CRUD operations
    public List<Inventory> getAllInventories();
    public Optional<Inventory> getInventoryById(Long id);
    public Inventory createInventory(Inventory inventory);
    public Inventory updateInventory(Long id, Inventory inventory);
    public void deleteInventory(Long id);

    //Business operations
    public List<Inventory> findWarehouseInventory(Long id);
    public List<Inventory> findWarehouseInventory(Warehouse warehouse);
    public List<Inventory> findProductInInventory(Long id);
    public List<Inventory> findProductInInventory(Product product);
    public List<Inventory> findProductInWarehouseInventory(Long productId, Long warehouseId);
    public List<Inventory> findProductInWarehouseInventory(Product product, Long warehouseId);
    public List<Inventory> findProductInWarehouseInventory(Long productId, Warehouse warehouse);
    public List<Inventory> findProductInWarehouseInventory(Product product, Warehouse warehouse);
    public List<Inventory> findLowStockInventory();

}
