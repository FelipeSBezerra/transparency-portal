package com.devfelipe.transparencyportal.common.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<EntityIdType, RequestDto, ResponseDto> {

    Page<ResponseDto> findAll(Pageable pageable);
    ResponseDto findById(EntityIdType entityId);
    ResponseDto create(RequestDto requestDto);
    ResponseDto update(EntityIdType entityId, RequestDto requestDto);
    void deleteById(EntityIdType entityId);
}
