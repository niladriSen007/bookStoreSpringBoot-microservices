package com.niladri.catalogue_service.domain.dtos;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductDto(String code, String name, String description, String imageUrl, BigDecimal price) {}
