package com.niladri.catalogue_service.domain.error;

public class ProductNotFoundException extends RuntimeException {


    public ProductNotFoundException(String message) {
        super(message);
    }
}
