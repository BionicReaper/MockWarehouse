package com.warehouse.service;

import com.warehouse.entity.Inventory;
import com.warehouse.entity.Product;
import com.warehouse.entity.Warehouse;
import com.warehouse.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    //CRUD operations
    @Override
    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id){
        return inventoryRepository.findById(id);
    }

    @Override
    public Inventory createInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory){
        return inventoryRepository.findById(id)
                .map( existing -> {
                    existing.setQuantity(inventory.getQuantity());
                    existing.setMinStock(inventory.getMinStock());
                    existing.setMaxStock(inventory.getMaxStock());
                    return inventoryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Inventory not found: " + id));
    }

    @Override
    public void deleteInventory(Long id){
        inventoryRepository.deleteById(id);
    }

    //Business operations
    @Override
    public List<Inventory> findWarehouseInventory(Long id){
        return inventoryRepository.findByWarehouseId(id);
    }

    @Override
    public List<Inventory> findWarehouseInventory(Warehouse warehouse){
        return inventoryRepository.findByWarehouseId(warehouse.getId());
    }

    @Override
    public List<Inventory> findProductInInventory(Long id){
        return inventoryRepository.findByProductId(id);
    }

    @Override
    public List<Inventory> findProductInInventory(Product product){
        return inventoryRepository.findByProductId(product.getId());
    }


    @Override
    public List<Inventory> findProductInWarehouseInventory(Long productId, Long warehouseId){
        return inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId);
    }


    @Override
    public List<Inventory> findProductInWarehouseInventory(Product product, Long warehouseId){
        return findProductInWarehouseInventory(product.getId(), warehouseId);
    }


    @Override
    public List<Inventory> findProductInWarehouseInventory(Long productId, Warehouse warehouse){
        return findProductInWarehouseInventory(productId, warehouse.getId());
    }


    @Override
    public List<Inventory> findProductInWarehouseInventory(Product product, Warehouse warehouse){
        return findProductInWarehouseInventory(product.getId(), warehouse.getId());
    }


    @Override
    public List<Inventory> findLowStockInventory(){
        return inventoryRepository.findLowStockItems();
    }

}
