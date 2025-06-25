package com.warehouse.dto.inventory;

import com.warehouse.dto.reference.ReferenceDTO;
import jakarta.validation.constraints.NotBlank;

public record CreateInventoryDTO(
        @NotBlank(message = "Quantity is required.")
        int quantity,

        @NotBlank(message = "Minimum stock is required.")
        int minStock,

        @NotBlank(message = "Maximum stock is required.")
        int maxStock,

        @NotBlank(message = "Warehouse id is required.")
        ReferenceDTO warehouse,

        @NotBlank(message = "Product id is required.")
        ReferenceDTO product
) {}
