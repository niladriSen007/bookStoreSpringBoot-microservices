package com.niladri.catalogue_service.domain.repositories;

import com.niladri.catalogue_service.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepository extends JpaRepository<Product, Long> {
}
