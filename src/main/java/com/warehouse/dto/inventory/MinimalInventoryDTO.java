package com.warehouse.dto.inventory;

import com.warehouse.dto.product.ResponseProductDTO;

public record MinimalInventoryDTO(
        Long id,
        int quantity,
        int minStock,
        int maxStock,
        ResponseProductDTO product
) {}
