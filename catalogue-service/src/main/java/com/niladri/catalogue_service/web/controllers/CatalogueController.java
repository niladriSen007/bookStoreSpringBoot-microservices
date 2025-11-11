package com.niladri.catalogue_service.web.controllers;

import com.niladri.catalogue_service.domain.dtos.PaginatedResult;
import com.niladri.catalogue_service.domain.dtos.ProductDto;
import com.niladri.catalogue_service.domain.services.CatalogueService;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core")
class CatalogueController {

    private final CatalogueService catalogueService;

    CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    CompletableFuture<ResponseEntity<PaginatedResult<ProductDto>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return catalogueService.getAllProducts(pageable).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{code}")
    CompletableFuture<ResponseEntity<ProductDto>> getProductByCode(@PathVariable(name = "code") String code) {
        return catalogueService.getProductByCode(code).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/products/{code}")
    ResponseEntity<ProductDto> getProductByCodeSync(@PathVariable(name = "code") String code) {
        ProductDto productDto = catalogueService.getProductByCodeSync(code);
        return ResponseEntity.ok(productDto);
    }
}
