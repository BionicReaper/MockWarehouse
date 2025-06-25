package com.warehouse.dto.inventory;

public record UpdateInventoryDTO(
        int quantity,

        int minStock,

        int maxStock
) {}
