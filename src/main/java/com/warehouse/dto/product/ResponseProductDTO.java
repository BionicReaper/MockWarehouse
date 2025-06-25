package com.warehouse.dto.product;

import java.math.BigDecimal;

public record ResponseProductDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String category,
        BigDecimal weight
) {}
