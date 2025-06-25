package com.warehouse.dto.reference;

import jakarta.validation.constraints.NotBlank;

public record ReferenceDTO(
        @NotBlank(message = "ID is required.")
        Long id
) {}
