package com.warehouse.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductDTO(
        @NotBlank(message = "Name is required.")
        @Size(max = 100, message = "Name cannot exceed 100 characters.")
        String name,

        @NotBlank(message = "Description is required.")
        @Size(max = 512, message = "Description cannot exceed 512 characters.")
        String description,

        @NotBlank(message = "Price is required.")
        BigDecimal price,

        @NotBlank(message = "Category is required.")
        @Size(max = 100, message = "Category cannot exceed 100 characters.")
        String category,

        @NotBlank(message = "Weight is required.")
        BigDecimal weight
) {}
