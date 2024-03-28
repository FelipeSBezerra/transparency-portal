package com.devfelipe.transparencyportal.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

/**
 * Base minimal response DTO class defining common properties for minimal response DTOs.
 */
@Getter
@Setter
public abstract class BaseMinimalResponseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private URI selfLink;
}
