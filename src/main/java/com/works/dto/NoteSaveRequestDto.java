package com.works.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.works.entity.Note}
 */
@Value
public class NoteSaveRequestDto implements Serializable {
    @NotNull
    @Size(min = 1, max = 15)
    @NotEmpty
    @NotBlank
    String title;
    @NotNull
    @Size(min = 1, max = 500)
    @NotEmpty
    @NotBlank
    String detail;
    int noteDay;
}