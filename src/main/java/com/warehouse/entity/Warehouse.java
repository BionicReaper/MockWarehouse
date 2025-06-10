package com.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Table(name = "warehouse")
public class Warehouse extends BaseEntity{

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "capacity", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacity;

    @Column(name = "manager_name", nullable = false, length = 100)
    private String managerName;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Inventory> inventories;

}
