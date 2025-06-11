package com.warehouse.repository;

import com.warehouse.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Inventory} entities.
 * Provides methods to perform CRUD operations and custom queries on inventory data.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Retrieves all inventory records with their associated product and warehouse fetched eagerly.
     *
     * @return a list of all inventory records with products and warehouses
     */
    @Override
    @Query("SELECT i FROM Inventory i LEFT JOIN FETCH i.product LEFT JOIN FETCH i.warehouse")
    List<Inventory> findAll();

    /**
     * Finds inventory records by the warehouse ID.
     *
     * @param warehouseId the ID of the warehouse
     * @return a list of inventory items stored in the specified warehouse
     */
    List<Inventory> findByWarehouseId(Long warehouseId);

    /**
     * Finds inventory records by the product ID.
     *
     * @param productId the ID of the product
     * @return a list of inventory items for the specified product
     */
    List<Inventory> findByProductId(Long productId);

    /**
     * Finds inventory records by both warehouse ID and product ID.
     *
     * @param warehouseId the ID of the warehouse
     * @param productId   the ID of the product
     * @return a list of inventory items matching both warehouse and product criteria
     */
    List<Inventory> findByWarehouseIdAndProductId(Long warehouseId, Long productId);

    /**
     * Finds inventory items where the quantity is less than the minimum stock threshold.
     *
     * @return a list of inventory items with low stock
     */
    @Query("SELECT i FROM Inventory i WHERE i.quantity < i.minStock")
    List<Inventory> findLowStockItems();
}
