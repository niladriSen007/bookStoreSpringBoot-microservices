package com.niladri.catalogue_service.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(
        name = "products",
        indexes = {@Index(name = "idx_product_code", columnList = "code")})
public class Product {
    public Product() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product code is required") private String code;

    @NotBlank(message = "Product name is required") @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl;

    @DecimalMin("0.1") @Column(nullable = false)
    @NotNull(message = "Product price is required") private BigDecimal price;

    public Product(String code, String name, String description, String imageUrl, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }
}
