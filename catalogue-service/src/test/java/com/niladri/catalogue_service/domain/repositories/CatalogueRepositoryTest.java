package com.niladri.catalogue_service.domain.repositories;

import com.niladri.catalogue_service.TestcontainersConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest(
        properties = {
            //                "spring.datasource.url=jdbc:tc:postgresql:18:///catalogue-db",
            "spring.test.database.replace=NONE"
        })
@Import(TestcontainersConfiguration.class)
class CatalogueRepositoryTest {

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Test
    void shouldGetAllProducts() {
        var products = catalogueRepository.findAll();
        Assertions.assertThat(products).hasSize(15);
    }

    @Test
    void shouldGetProductByCode() {
        var productOpt = catalogueRepository.findByCode("P100");
        Assertions.assertThat(productOpt).isPresent();
        var product = productOpt.get();
        Assertions.assertThat(product.getName()).isEqualTo("The Hunger Games");
        Assertions.assertThat(product.getPrice()).isEqualByComparingTo("34.00");
    }

    @Test
    void shouldReturnEmptyForNonExistingProductCode() {
        var productOpt = catalogueRepository.findByCode("NON_EXISTING_CODE");
        Assertions.assertThat(productOpt).isNotPresent();
    }
}
