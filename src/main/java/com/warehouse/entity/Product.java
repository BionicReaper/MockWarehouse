package com.warehouse.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends BaseEntity{

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

}
