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

public abstract class BaseServiceImp<EntityClass extends BaseModel, EntityIdType,RequestDto, ResponseDto, Specification extends BaseSpecification<EntityClass>> implements BaseService<EntityClass, EntityIdType, RequestDto, ResponseDto, Specification>{

    private final Class<EntityClass> entityClass;
    private final BaseRepository<EntityClass, EntityIdType> repository;
    private final BaseMapper<EntityClass, ResponseDto, RequestDto> baseMapper;

    protected BaseServiceImp(Class<EntityClass> entityClass, BaseRepository<EntityClass, EntityIdType> repository, BaseMapper<EntityClass, ResponseDto, RequestDto> baseMapper) {
        this.entityClass = entityClass;
        this.repository = repository;
        this.baseMapper = baseMapper;
    }

    @Override
    public Page<ResponseDto> findAll(Specification Specification, Pageable pageable) {
        Page<EntityClass> entityClassPage = _getRepository().findAll(Specification ,pageable);
        List<ResponseDto> responseDtoList = entityClassPage.getContent().stream()
                .map(baseMapper::mapToResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(responseDtoList, entityClassPage.getPageable(), entityClassPage.getTotalElements());
    }

    @Override
    public ResponseDto findById(EntityIdType entityId) {
        return baseMapper.mapToResponseDto(findByIdReturnsEntity(entityId));
    }

    @Override
    public EntityClass findByIdReturnsEntity(EntityIdType entityId) {
        return _getRepository().findById(entityId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("There is no resource of type %s with the id %s in the database.", this.entityClass.getSimpleName(), entityId.toString())));
    }

    @Override
    public ResponseDto create(RequestDto requestDto) {
        EntityClass newEntity = _createEntityFromDto(requestDto);
        EntityClass savedEntity = _getRepository().save(newEntity);
        return baseMapper.mapToResponseDto(savedEntity);
    }

    @Override
    public ResponseDto update(EntityIdType entityId, RequestDto requestDto) {
        EntityClass updatedEntity = _updateEntityFromDto(entityId, requestDto);
        return baseMapper.mapToResponseDto(_getRepository().save(updatedEntity));
    }

    @Override
    public void deleteById(EntityIdType entityId) {
        if (!_getRepository().existsById(entityId)) {
            throw new ResourceNotFoundException(
                    String.format("There is no resource of type %s with the id %s in the database.", this.entityClass.getSimpleName(), entityId.toString()));
        }
        _checkDataIntegrityViolationForDeletion(entityId);
        _getRepository().deleteById(entityId);
    }

    protected abstract EntityClass _createEntityFromDto(RequestDto requestDto);
    protected abstract EntityClass _updateEntityFromDto(EntityIdType entityId, RequestDto requestDto);
    protected abstract void _checkDataIntegrityViolationForDeletion(EntityIdType entityId);
    private BaseRepository<EntityClass, EntityIdType> _getRepository() {
        return this.repository;
    }

}
