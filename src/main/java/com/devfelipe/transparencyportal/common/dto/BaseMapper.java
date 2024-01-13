package com.devfelipe.transparencyportal.common.dto;

/**
 * Base mapper interface defining the contract for mapping entities to response DTOs.
 *
 * @param <T>   The type of the entity.
 * @param <Resp> The type of the response DTO.
 */
public interface BaseMapper<T, Resp> {

    Resp mapToResponseDto(T entityClass);
}
