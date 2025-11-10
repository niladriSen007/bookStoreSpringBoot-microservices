package com.niladri.catalogue_service.domain.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(String code, String name, String description, String imageUrl, BigDecimal price) {
}
