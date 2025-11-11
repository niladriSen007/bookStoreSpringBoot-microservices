package com.niladri.catalogue_service;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "catalogue")
public record ApplicationProperties(
        @DefaultValue("0")
        @Min(0)
        int page,
        @DefaultValue("5")
        @Min(1)
        int size
) {
}
