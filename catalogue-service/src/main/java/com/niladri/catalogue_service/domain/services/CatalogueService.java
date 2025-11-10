package com.niladri.catalogue_service.domain.services;

import com.niladri.catalogue_service.domain.dtos.PaginatedResult;
import com.niladri.catalogue_service.domain.dtos.ProductDto;
import com.niladri.catalogue_service.domain.mapper.ModelMapper;
import com.niladri.catalogue_service.domain.repositories.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CatalogueService {
    private final CatalogueRepository catalogueRepository;

    CatalogueService(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Async("taskExecutor")
    public CompletableFuture<PaginatedResult<ProductDto>> getAllProducts(Pageable pageable) {
        Page<ProductDto> map = catalogueRepository.findAll(pageable).map(ModelMapper::mapToProductDto);
        return CompletableFuture.completedFuture(new PaginatedResult<>(
                map.getContent(),
                map.getTotalElements(),
                map.getNumber()+1,
                map.getTotalPages(),
                map.isFirst(),
                map.isLast(),
                map.hasNext(),
                map.hasPrevious()
        ));
    }
}
