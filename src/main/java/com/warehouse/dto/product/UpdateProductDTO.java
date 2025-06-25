package com.warehouse.dto.product;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductDTO(
        @Size(max = 100, message = "Name cannot exceed 100 characters.")
        String name,

        @Size(max = 512, message = "Description cannot exceed 512 characters.")
        String description,

        BigDecimal price,

        @Size(max = 100, message = "Category cannot exceed 100 characters.")
        String category,

        BigDecimal weight
) {}
