package com.warehouse.dto.inventory;

import com.warehouse.dto.warehouse.MinimalWarehouseDTO;
import com.warehouse.dto.product.ResponseProductDTO;

public record ResponseInventoryDTO(
        Long id,
        int quantity,
        int minStock,
        int maxStock,
        MinimalWarehouseDTO warehouse,
        ResponseProductDTO product
) {}
