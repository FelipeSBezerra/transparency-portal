package com.devfelipe.transparencyportal.common.domain;

import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.common.domain.model.BaseModel;
import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.common.infra.BaseRepository;
import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract implementation of the BaseService interface, providing common CRUD operations for entities.
 *
 * @param <T>   The type of the entity extending {@link BaseModel}.
 * @param <ID>  The type of the entity's unique identifier.
 * @param <R>   The type of the request DTO.
 * @param <Resp> The type of the response DTO.
 * @param <S>   The type of the specification extending {@link BaseSpecification}.
 */
public abstract class BaseServiceImp<T extends BaseModel, ID, R, Resp, S extends BaseSpecification<T>> implements BaseService<T, ID, R, Resp, S>{

    private final Class<T> entityClass;
    private final BaseRepository<T, ID> repository;
    private final BaseMapper<T, Resp> baseMapper;

    protected BaseServiceImp(Class<T> entityClass, BaseRepository<T, ID> repository, BaseMapper<T, Resp> baseMapper) {
        this.entityClass = entityClass;
        this.repository = repository;
        this.baseMapper = baseMapper;
    }

    @Override
    public Page<Resp> findAll(S specification, Pageable pageable) {
        Page<T> entityClassPage = _getRepository().findAll(specification ,pageable);
        List<Resp> responseDtoList = entityClassPage.getContent().stream()
                .map(baseMapper::mapToResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(responseDtoList, entityClassPage.getPageable(), entityClassPage.getTotalElements());
    }

    @Override
    public Resp findById(ID entityId) {
        return baseMapper.mapToResponseDto(findByIdReturnsEntity(entityId));
    }

    @Override
    public T findByIdReturnsEntity(ID entityId) {
        return _getRepository().findById(entityId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("There is no resource of type %s with the id %s in the database.", this.entityClass.getSimpleName(), entityId.toString())));
    }

    @Override
    public Resp create(R requestDto) {
        T newEntity = _createEntityFromDto(requestDto);
        T savedEntity = _getRepository().save(newEntity);
        return baseMapper.mapToResponseDto(savedEntity);
    }

    @Override
    public Resp update(ID entityId, R requestDto) {
        T updatedEntity = _updateEntityFromDto(entityId, requestDto);
        return baseMapper.mapToResponseDto(_getRepository().save(updatedEntity));
    }

    @Override
    public void deleteById(ID entityId) {
        if (!_getRepository().existsById(entityId)) {
            throw new ResourceNotFoundException(
                    String.format("There is no resource of type %s with the id %s in the database.", this.entityClass.getSimpleName(), entityId.toString()));
        }
        _checkDataIntegrityViolationForDeletion(entityId);
        _getRepository().deleteById(entityId);
    }

    protected abstract T _createEntityFromDto(R requestDto);
    protected abstract T _updateEntityFromDto(ID entityId, R requestDto);
    protected abstract void _checkDataIntegrityViolationForDeletion(ID entityId);
    private BaseRepository<T, ID> _getRepository() {
        return this.repository;
    }

}
