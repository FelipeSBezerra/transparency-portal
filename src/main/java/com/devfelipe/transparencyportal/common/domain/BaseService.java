package com.devfelipe.transparencyportal.common.domain;

import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface defining common CRUD operations for entities.
 *
 * @param <T>   The type of the entity.
 * @param <ID>  The type of the entity's unique identifier.
 * @param <R>   The type of the request DTO.
 * @param <Resp> The type of the response DTO.
 * @param <S>   The type of the specification extending {@link BaseSpecification}.
 */
public interface BaseService<T, ID, R, Resp, S extends BaseSpecification<T>> {

    Page<Resp> findAll(S specification, Pageable pageable);
    Resp findById(ID entityId);
    T findByIdReturnsEntity(ID entityId);
    Resp create(R requestDto);
    Resp update(ID entityId, R requestDto);
    void deleteById(ID entityId);
}
