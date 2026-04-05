package com.works.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.works.entity.Product}
 */
@Value
public class ProductSaveRequestDto implements Serializable {
    @NotNull
    @Size(min = 1, max = 15)
    @NotEmpty
    @NotBlank
    String title;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    String description;
    @NotNull
    @Min(10)
    @Max(1000000)
    BigDecimal price;
}