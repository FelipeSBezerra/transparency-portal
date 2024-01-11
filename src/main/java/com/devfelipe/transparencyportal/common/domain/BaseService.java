package com.devfelipe.transparencyportal.common.domain;

import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<EntityClass, EntityIdType, RequestDto, ResponseDto, Specification extends BaseSpecification<EntityClass>> {

    Page<ResponseDto> findAll(Specification Specification, Pageable pageable);
    ResponseDto findById(EntityIdType entityId);
    EntityClass findByIdReturnsEntity(EntityIdType entityId);
    ResponseDto create(RequestDto requestDto);
    ResponseDto update(EntityIdType entityId, RequestDto requestDto);
    void deleteById(EntityIdType entityId);
}
