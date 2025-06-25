package com.warehouse.dto.mapper;
import com.warehouse.dto.product.CreateProductDTO;
import com.warehouse.dto.product.ResponseProductDTO;
import com.warehouse.dto.product.UpdateProductDTO;
import com.warehouse.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ResponseProductDTO toResponseDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(CreateProductDTO createProductDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(UpdateProductDTO updateProductDTO);

    List<ResponseProductDTO> toResponseDto(List<Product> productList);
}
