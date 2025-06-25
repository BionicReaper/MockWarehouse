package com.warehouse.dto.warehouse;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateWarehouseDTO(
        @Size(max = 100, message = "Name cannot exceed 100 characters.")
        String name,

        @Size(max = 100, message = "Name cannot exceed 100 characters.")
        String address,

        BigDecimal capacity,

        @Size(max = 100, message = "Manager name cannot exceed 100 characters.")
        String managerName
) {}
