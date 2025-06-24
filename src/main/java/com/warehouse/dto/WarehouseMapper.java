package com.warehouse.dto;
import com.warehouse.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "managerName", target = "managerName")
    @Mapping(source = "inventories", target = "inventories")
    ResponseWarehouseDTO toResponseDto(Warehouse warehouse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target =  "inventories", ignore = true)
    Warehouse toEntity(CreateWarehouseDTO createWarehouseDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target =  "inventories", ignore = true)
    Warehouse toEntity(UpdateWarehouseDTO updateWarehouseDTO);

    List<ResponseWarehouseDTO> toResponseDto(List<Warehouse> warehouseList);
}
