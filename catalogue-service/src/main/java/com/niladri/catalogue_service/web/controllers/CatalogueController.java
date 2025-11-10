package com.niladri.catalogue_service.web.controllers;

import com.niladri.catalogue_service.domain.dtos.PaginatedResult;
import com.niladri.catalogue_service.domain.dtos.ProductDto;
import com.niladri.catalogue_service.domain.services.CatalogueService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/core")
class CatalogueController {

    private final CatalogueService catalogueService;

    CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    DeferredResult<ResponseEntity<PaginatedResult<ProductDto>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        DeferredResult<ResponseEntity<PaginatedResult<ProductDto>>> deferredResult = new DeferredResult<>();
        catalogueService.getAllProducts(pageable).whenComplete((pageResult, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(ResponseEntity.ok(pageResult));
            }
        });
        return deferredResult;
    }
}
