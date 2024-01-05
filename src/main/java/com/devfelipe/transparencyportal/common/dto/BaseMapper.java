package com.devfelipe.transparencyportal.common.dto;

public interface BaseMapper<EntityClass, ResponseDto, RequestDto> {

    ResponseDto mapToResponseDto(EntityClass entityClass);
}
