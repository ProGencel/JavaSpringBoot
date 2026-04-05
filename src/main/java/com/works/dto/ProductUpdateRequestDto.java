package com.works.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.works.entity.Product}
 */
@Value
public class ProductUpdateRequestDto implements Serializable {
    @NotNull
    @Min(1)
    @Max(Long.MAX_VALUE)
    Long id;
    @NotNull
    @Size(min = 1, max = 15)
    @NotEmpty
    @NotBlank
    String title;
    @NotNull
    @Size(min = 1, max = 50)
    @NotEmpty
    @NotBlank
    String description;
    @NotNull
    @Min(1)
    @Max(1000000)
    BigDecimal price;
}