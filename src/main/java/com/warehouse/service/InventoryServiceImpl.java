package com.warehouse.service;

import com.warehouse.entity.Inventory;
import com.warehouse.entity.Product;
import com.warehouse.entity.Warehouse;
import com.warehouse.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link Inventory} entities.
 * Provides CRUD operations and business logic for inventory management.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * Constructs an InventoryServiceImpl with the given {@code InventoryRepository}.
     *
     * @param inventoryRepository repository for inventory persistence operations
     */
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Retrieves all inventory records.
     *
     * @return list of all inventories
     */
    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    /**
     * Retrieves an inventory record by its ID.
     *
     * @param id the inventory ID
     * @return an Optional containing the found inventory or empty if not found
     */
    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    /**
     * Creates a new inventory record.
     *
     * @param inventory the inventory entity to create
     * @return the saved inventory entity
     */
    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    /**
     * Updates an existing inventory record by ID.
     * Only quantity, minStock, and maxStock fields are updated.
     *
     * @param id        the ID of the inventory to update
     * @param inventory the inventory data to update
     * @return the updated inventory entity
     * @throws RuntimeException if the inventory with given ID is not found
     */
    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        return inventoryRepository.findById(id)
                .map(existing -> {
                    existing.setQuantity(inventory.getQuantity());
                    existing.setMinStock(inventory.getMinStock());
                    existing.setMaxStock(inventory.getMaxStock());
                    return inventoryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Inventory not found: " + id));
    }

    /**
     * Deletes an inventory record by its ID.
     *
     * @param id the ID of the inventory to delete
     */
    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    /**
     * Finds all inventory records in a warehouse by warehouse ID.
     *
     * @param id the warehouse ID
     * @return list of inventory records in the specified warehouse
     */
    @Override
    public List<Inventory> findWarehouseInventory(Long id) {
        return inventoryRepository.findByWarehouseId(id);
    }

    /**
     * Finds all inventory records in a warehouse by warehouse entity.
     *
     * @param warehouse the warehouse entity
     * @return list of inventory records in the specified warehouse
     */
    @Override
    public List<Inventory> findWarehouseInventory(Warehouse warehouse) {
        return inventoryRepository.findByWarehouseId(warehouse.getId());
    }

    /**
     * Finds all inventory records containing a specific product by product ID.
     *
     * @param id the product ID
     * @return list of inventory records for the specified product
     */
    @Override
    public List<Inventory> findProductInInventory(Long id) {
        return inventoryRepository.findByProductId(id);
    }

    /**
     * Finds all inventory records containing a specific product by product entity.
     *
     * @param product the product entity
     * @return list of inventory records for the specified product
     */
    @Override
    public List<Inventory> findProductInInventory(Product product) {
        return inventoryRepository.findByProductId(product.getId());
    }

    /**
     * Finds inventory records for a specific product in a specific warehouse.
     *
     * @param productId   the product ID
     * @param warehouseId the warehouse ID
     * @return list of matching inventory records
     */
    @Override
    public List<Inventory> findProductInWarehouseInventory(Long productId, Long warehouseId) {
        return inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId);
    }

    /**
     * Finds inventory records for a specific product entity in a specific warehouse by warehouse ID.
     *
     * @param product     the product entity
     * @param warehouseId the warehouse ID
     * @return list of matching inventory records
     */
    @Override
    public List<Inventory> findProductInWarehouseInventory(Product product, Long warehouseId) {
        return findProductInWarehouseInventory(product.getId(), warehouseId);
    }

    /**
     * Finds inventory records for a specific product by product ID in a specific warehouse entity.
     *
     * @param productId the product ID
     * @param warehouse the warehouse entity
     * @return list of matching inventory records
     */
    @Override
    public List<Inventory> findProductInWarehouseInventory(Long productId, Warehouse warehouse) {
        return findProductInWarehouseInventory(productId, warehouse.getId());
    }

    /**
     * Finds inventory records for a specific product entity in a specific warehouse entity.
     *
     * @param product   the product entity
     * @param warehouse the warehouse entity
     * @return list of matching inventory records
     */
    @Override
    public List<Inventory> findProductInWarehouseInventory(Product product, Warehouse warehouse) {
        return findProductInWarehouseInventory(product.getId(), warehouse.getId());
    }

    /**
     * Finds all inventory records with low stock (below minimum stock threshold).
     *
     * @return list of inventory records with low stock
     */
    @Override
    public List<Inventory> findLowStockInventory() {
        return inventoryRepository.findLowStockItems();
    }
}
