package com.devfelipe.transparencyportal.common.domain;

import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.common.domain.model.BaseModel;
import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImp<EntityClass extends BaseModel, EntityIdType,RequestDto, ResponseDto> implements BaseService<EntityClass, EntityIdType, RequestDto, ResponseDto>{

    private final Class<EntityClass> entityClass;
    private final JpaRepository<EntityClass, EntityIdType> repository;
    private final BaseMapper<EntityClass, ResponseDto, RequestDto> baseMapper;

    protected BaseServiceImp(Class<EntityClass> entityClass, JpaRepository<EntityClass, EntityIdType> repository, BaseMapper<EntityClass, ResponseDto, RequestDto> baseMapper) {
        this.entityClass = entityClass;
        this.repository = repository;
        this.baseMapper = baseMapper;
    }

    @Override
    public Page<ResponseDto> findAll(Pageable pageable) {
        Page<EntityClass> entityClassPage = _getRepository().findAll(pageable);
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
        _getRepository().deleteById(entityId);
    }

    protected abstract EntityClass _createEntityFromDto(RequestDto requestDto);
    protected abstract EntityClass _updateEntityFromDto(EntityIdType entityId, RequestDto requestDto);
    private JpaRepository<EntityClass, EntityIdType> _getRepository() {
        return this.repository;
    }

}
