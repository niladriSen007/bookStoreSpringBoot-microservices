package com.niladri.catalogue_service.domain.services;

import com.niladri.catalogue_service.domain.dtos.PaginatedResult;
import com.niladri.catalogue_service.domain.dtos.ProductDto;
import com.niladri.catalogue_service.domain.entity.Product;
import com.niladri.catalogue_service.domain.error.ProductNotFoundException;
import com.niladri.catalogue_service.domain.mapper.ModelMapper;
import com.niladri.catalogue_service.domain.repositories.CatalogueRepository;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
                        map.getNumber() + 1,
                        map.getTotalPages(),
                        map.isFirst(),
                        map.isLast(),
                        map.hasNext(),
                        map.hasPrevious()))
                .handle((result, ex) -> {
                    if (ex != null) {
                        throw new CompletionException(ex);
                    }
                    return result;
                });
    }

    @Async("taskExecutor")
    public CompletableFuture<ProductDto> getProductByCode(String code) {
        Optional<Product> byCode = catalogueRepository.findByCode(code);
        Product product =
                byCode.orElseThrow(() -> new ProductNotFoundException("Product with code " + code + " not found"));
        return CompletableFuture.completedFuture(ModelMapper.mapToProductDto(product))
                .handle((prod, ex) -> {
                    if (ex != null) {
                        throw new ProductNotFoundException("Product with code " + code + " not found");
                    }
                    return prod;
                });
    }

    public ProductDto getProductByCodeSync(String code) {
        Optional<Product> byCode = catalogueRepository.findByCode(code);
        Product product =
                byCode.orElseThrow(() -> new ProductNotFoundException("Product with code " + code + " not found..."));
        return ModelMapper.mapToProductDto(product);
    }
}
