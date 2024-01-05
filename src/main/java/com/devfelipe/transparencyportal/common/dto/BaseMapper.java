package com.devfelipe.transparencyportal.common.dto;

public interface BaseMapper<EntityClass, ResponseDto, RequestDto> {

    ResponseDto mapToResponseDto(EntityClass entityClass);
    EntityClass mapToEntityClass(RequestDto requestDto);
    EntityClass updateFromDto(EntityClass entity, RequestDto requestDto);
}
