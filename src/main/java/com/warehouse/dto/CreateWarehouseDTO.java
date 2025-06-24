package com.warehouse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateWarehouseDTO(
    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    String name,

    @NotBlank(message = "Address is required.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    String address,

    @NotBlank(message = "Capacity is required.")
    BigDecimal capacity,

    @NotBlank(message = "Manager name is required.")
    @Size(max = 100, message = "Manager name cannot exceed 100 characters.")
    String managerName
) {}
