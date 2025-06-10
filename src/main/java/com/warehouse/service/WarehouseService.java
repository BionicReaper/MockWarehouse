package com.warehouse.service;

import com.warehouse.entity.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    //CRUD operations
    List<Warehouse> getAllWarehouses();
    Optional<Warehouse> getWarehouseById(Long id);
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(Long id, Warehouse warehouse);
    void deleteWarehouse(Long id);

    //Business operations
    List<Warehouse> findWarehousesByName(String name);
    List<Warehouse> findWarehousesByCapacity(BigDecimal minCapacity);
    List<Warehouse> search(String name, BigDecimal minCapacity);

}