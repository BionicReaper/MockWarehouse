package com.warehouse.repository;

import com.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Override
    @Query("SELECT w FROM Warehouse w LEFT JOIN FETCH w.inventories i LEFT JOIN FETCH i.product")
    List<Warehouse> findAll();

    List<Warehouse> findByNameContainingIgnoreCase(String name);

    List<Warehouse> findByCapacityGreaterThan(BigDecimal capacity);

    @Query("SELECT w FROM Warehouse w WHERE w.managerName IS NOT NULL")
    List<Warehouse> findWarehousesWithManager();

}
