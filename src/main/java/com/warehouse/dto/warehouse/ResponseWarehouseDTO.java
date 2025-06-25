package com.warehouse.dto.warehouse;

import com.warehouse.dto.inventory.MinimalInventoryDTO;

import java.math.BigDecimal;
import java.util.List;

public record ResponseWarehouseDTO (
        Long id,
        String name,
        String address,
        BigDecimal capacity,
        String managerName,
        List<MinimalInventoryDTO> inventories
) {}
