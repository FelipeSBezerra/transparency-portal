package com.devfelipe.transparencyportal.fundingsource.dto;

import jakarta.validation.constraints.NotBlank;

public record FundingSourceRequestDto (
        @NotBlank(message = "The name field cannot be empty")
        String name
) {
}
