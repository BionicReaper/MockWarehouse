package com.warehouse.dto;

import com.warehouse.entity.Inventory;

import java.math.BigDecimal;
import java.util.List;

public record ResponseWarehouseDTO (
        Long id,
        String name,
        String address,
        BigDecimal capacity,
        String managerName,
        List<Inventory> inventories
) {}
