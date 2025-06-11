package com.warehouse.service;

import com.warehouse.entity.Inventory;
import com.warehouse.entity.Product;
import com.warehouse.entity.Warehouse;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link Inventory} entities.
 * Defines CRUD operations and business-specific queries related to inventory management.
 */
public interface InventoryService {

    /**
     * Retrieves all inventory records.
     *
     * @return a list of all inventories
     */
    List<Inventory> getAllInventories();

    /**
     * Retrieves an inventory record by its ID.
     *
     * @param id the ID of the inventory
     * @return an {@link Optional} containing the inventory if found, or empty otherwise
     */
    Optional<Inventory> getInventoryById(Long id);

    /**
     * Creates a new inventory record.
     *
     * @param inventory the inventory to create
     * @return the created inventory
     */
    Inventory createInventory(Inventory inventory);

    /**
     * Updates an existing inventory record identified by its ID.
     *
     * @param id        the ID of the inventory to update
     * @param inventory the updated inventory data
     * @return the updated inventory
     */
    Inventory updateInventory(Long id, Inventory inventory);

    /**
     * Deletes an inventory record by its ID.
     *
     * @param id the ID of the inventory to delete
     */
    void deleteInventory(Long id);

    /**
     * Finds all inventory records associated with a specific warehouse by its ID.
     *
     * @param id the warehouse ID
     * @return a list of inventory items in the specified warehouse
     */
    List<Inventory> findWarehouseInventory(Long id);

    /**
     * Finds all inventory records associated with a specific warehouse.
     *
     * @param warehouse the warehouse entity
     * @return a list of inventory items in the specified warehouse
     */
    List<Inventory> findWarehouseInventory(Warehouse warehouse);

    /**
     * Finds all inventory records for a specific product by its ID.
     *
     * @param id the product ID
     * @return a list of inventory items for the specified product
     */
    List<Inventory> findProductInInventory(Long id);

    /**
     * Finds all inventory records for a specific product.
     *
     * @param product the product entity
     * @return a list of inventory items for the specified product
     */
    List<Inventory> findProductInInventory(Product product);

    /**
     * Finds inventory records for a specific product in a specific warehouse by their IDs.
     *
     * @param productId   the product ID
     * @param warehouseId the warehouse ID
     * @return a list of matching inventory items
     */
    List<Inventory> findProductInWarehouseInventory(Long productId, Long warehouseId);

    /**
     * Finds inventory records for a specific product entity in a specific warehouse by warehouse ID.
     *
     * @param product     the product entity
     * @param warehouseId the warehouse ID
     * @return a list of matching inventory items
     */
    List<Inventory> findProductInWarehouseInventory(Product product, Long warehouseId);

    /**
     * Finds inventory records for a specific product by product ID in a specific warehouse entity.
     *
     * @param productId the product ID
     * @param warehouse the warehouse entity
     * @return a list of matching inventory items
     */
    List<Inventory> findProductInWarehouseInventory(Long productId, Warehouse warehouse);

    /**
     * Finds inventory records for a specific product entity in a specific warehouse entity.
     *
     * @param product   the product entity
     * @param warehouse the warehouse entity
     * @return a list of matching inventory items
     */
    List<Inventory> findProductInWarehouseInventory(Product product, Warehouse warehouse);

    /**
     * Finds all inventory items with quantity less than their minimum stock threshold.
     *
     * @return a list of low stock inventory items
     */
    List<Inventory> findLowStockInventory();

}
