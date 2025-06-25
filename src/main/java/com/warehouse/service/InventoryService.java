package com.warehouse.service;

import com.warehouse.dto.inventory.CreateInventoryDTO;
import com.warehouse.dto.inventory.ResponseInventoryDTO;
import com.warehouse.dto.inventory.UpdateInventoryDTO;
import com.warehouse.dto.reference.ReferenceDTO;
import com.warehouse.entity.Inventory;

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
    List<ResponseInventoryDTO> getAllInventories();

    /**
     * Retrieves an inventory record by its ID.
     *
     * @param id the ID of the inventory
     * @return an {@link Optional} containing the inventory if found, or empty otherwise
     */
    Optional<ResponseInventoryDTO> getInventoryById(Long id);

    /**
     * Creates a new inventory record.
     *
     * @param inventoryDTO the inventory to create
     * @return the created inventory
     */
    ResponseInventoryDTO createInventory(CreateInventoryDTO inventoryDTO);

    /**
     * Updates an existing inventory record identified by its ID.
     *
     * @param id        the ID of the inventory to update
     * @param inventoryDTO the updated inventory data
     * @return the updated inventory
     */
    ResponseInventoryDTO updateInventory(Long id, UpdateInventoryDTO inventoryDTO);

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
    List<ResponseInventoryDTO> findWarehouseInventory(Long id);

    /**
     * Finds all inventory records associated with a specific warehouse.
     *
     * @param warehouseDTO the warehouse entity
     * @return a list of inventory items in the specified warehouse
     */
    List<ResponseInventoryDTO> findWarehouseInventory(ReferenceDTO warehouseDTO);

    /**
     * Finds all inventory records for a specific product by its ID.
     *
     * @param id the product ID
     * @return a list of inventory items for the specified product
     */
    List<ResponseInventoryDTO> findProductInInventory(Long id);

    /**
     * Finds all inventory records for a specific product.
     *
     * @param productDTO the product entity
     * @return a list of inventory items for the specified product
     */
    List<ResponseInventoryDTO> findProductInInventory(ReferenceDTO productDTO);

    /**
     * Finds inventory records for a specific product in a specific warehouse by their IDs.
     *
     * @param productId   the product ID
     * @param warehouseId the warehouse ID
     * @return a list of matching inventory items
     */
    List<ResponseInventoryDTO> findProductInWarehouseInventory(Long productId, Long warehouseId);

    /**
     * Finds inventory records for a specific product entity in a specific warehouse by warehouse ID.
     *
     * @param productDTO     the product entity
     * @param warehouseId    the warehouse ID
     * @return a list of matching inventory items
     */
    List<ResponseInventoryDTO> findProductInWarehouseInventory(ReferenceDTO productDTO, Long warehouseId);

    /**
     * Finds inventory records for a specific product by product ID in a specific warehouse entity.
     *
     * @param productId    the product ID
     * @param warehouseDTO the warehouse entity
     * @return a list of matching inventory items
     */
    List<ResponseInventoryDTO> findProductInWarehouseInventory(Long productId, ReferenceDTO warehouseDTO);

    /**
     * Finds inventory records for a specific product entity in a specific warehouse entity.
     *
     * @param productDTO   the product entity
     * @param warehouseDTO the warehouse entity
     * @return a list of matching inventory items
     */
    List<ResponseInventoryDTO> findProductInWarehouseInventory(ReferenceDTO productDTO, ReferenceDTO warehouseDTO);

    /**
     * Finds all inventory items with quantity less than their minimum stock threshold.
     *
     * @return a list of low stock inventory items
     */
    List<ResponseInventoryDTO> findLowStockInventory();

}
