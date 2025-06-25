package com.warehouse.dto.mapper;
import com.warehouse.dto.reference.ReferenceDTO;
import com.warehouse.entity.Product;
import com.warehouse.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "capacity", ignore = true)
    @Mapping(target = "managerName", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target =  "inventories", ignore = true)
    Warehouse toWarehouseEntity(ReferenceDTO referenceDTO);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "weight", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toProductEntity(ReferenceDTO referenceDTO);
}
