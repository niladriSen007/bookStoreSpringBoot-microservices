package com.niladri.catalogue_service.domain.mapper;

import com.niladri.catalogue_service.domain.dtos.ProductDto;
import com.niladri.catalogue_service.domain.entity.Product;

public class ModelMapper {
    public static Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.code(),
                productDto.name(),
                productDto.description(),
                productDto.imageUrl(),
                productDto.price()
        );
    }

    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice()
        );
    }
}
