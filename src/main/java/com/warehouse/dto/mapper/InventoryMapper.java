package com.warehouse.dto.mapper;
import com.warehouse.dto.inventory.CreateInventoryDTO;
import com.warehouse.dto.inventory.MinimalInventoryDTO;
import com.warehouse.dto.inventory.ResponseInventoryDTO;
import com.warehouse.dto.inventory.UpdateInventoryDTO;
import com.warehouse.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, ReferenceMapper.class, MinimalWarehouseMapper.class})
public interface InventoryMapper {

    ResponseInventoryDTO toResponseDto(Inventory inventory);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Inventory toEntity(CreateInventoryDTO createInventoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "product", ignore = true)
    Inventory toEntity(UpdateInventoryDTO updateInventoryDTO);

    List<ResponseInventoryDTO> toResponseDto(List<Inventory> inventoryList);

    List<MinimalInventoryDTO> toMinimalDto(List<Inventory> inventories);
}
