package com.devfelipe.transparencyportal.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

/**
 * Base response DTO class defining common properties for response DTOs.
 *
 * @param <ID> The type of the identifier.
 */
@Getter
@Setter
public abstract class BaseResponseDto<ID> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private URI selfLink;
    public abstract ID getId();
}
