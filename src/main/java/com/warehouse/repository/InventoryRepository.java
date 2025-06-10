package com.warehouse.repository;

import com.warehouse.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Override
    @Query("SELECT i FROM Inventory i LEFT JOIN FETCH i.product LEFT JOIN FETCH i.warehouse")
    List<Inventory> findAll();

    List<Inventory> findByWarehouseId(Long warehouseId);

    List<Inventory> findByProductId(Long productId);

    List<Inventory> findByWarehouseIdAndProductId(Long warehouseId, Long productId);

    @Query("SELECT i FROM Inventory i WHERE i.quantity < i.minStock")
    List<Inventory> findLowStockItems();
}
