package com.niladri.catalogue_service.domain.dtos;

import java.util.List;

public record PaginatedResult<T>(
        List<T> data,
        long totalElements,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious
) {
}
