package com.devfelipe.transparencyportal.common.resource;

import com.devfelipe.transparencyportal.common.domain.BaseService;
import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.common.infra.specification.BaseSpecification;
import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateoasService;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.PagedModelWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.ResponseDtoWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.UriList;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This abstract class serves as a base for generic controllers.
 *
 * @param <T>       Type of the entity handled by the controller.
 * @param <ID>      Type of the unique identifier for the entity.
 * @param <R>       Type of the request DTO.
 * @param <Resp>    Type of the response DTO, extending {@link BaseResponseDto}.
 * @param <S>       Type of the specification, extending {@link BaseSpecification}.
 */
public abstract class BaseController<T, ID, R, Resp extends BaseResponseDto<ID>, S extends BaseSpecification<T>> {

    private final BaseService<T, ID, R, Resp, S> baseService;
    private final BaseHateoasService<ID, Resp, S> baseHateoasService;

    protected BaseController(BaseService<T, ID, R, Resp, S> baseService, BaseHateoasService<ID, Resp, S> baseHateoasService) {
        this.baseService = baseService;
        this.baseHateoasService = baseHateoasService;
    }


    @GetMapping
    public ResponseEntity<PagedModelWithActions<Resp>> findAll(S specification, Pageable pageable) {
        _checkBaseServiceNotNull();
        Page<Resp> responseDtoPage = baseService.findAll(specification, pageable);
        String createUri = WebMvcLinkBuilder.linkTo(methodOn(getClass()).create(null)).toUri().toString();
        return ResponseEntity.ok(this.baseHateoasService.addActionsInFindAll(responseDtoPage, createUri, specification, pageable));
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDtoWithActions<ID>> findById(@PathVariable ID entityId) {
        _checkBaseServiceNotNull();
        BaseResponseDto<ID> result = baseService.findById(entityId);
        return ResponseEntity.ok(this.baseHateoasService.addActionsInFindById(result, _createUriList(entityId)));
    }

    @PostMapping
    public ResponseEntity<Resp> create(@RequestBody @Valid R requestDto) {
        _checkBaseServiceNotNull();
        Resp savedEntity = baseService.create(requestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{entityId}").buildAndExpand(savedEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{entityId}")
    public ResponseEntity<Resp> update(@PathVariable ID entityId, @RequestBody @Valid R requestDto) {
        _checkBaseServiceNotNull();
        return ResponseEntity.ok(baseService.update(entityId, requestDto));
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<Void> deleteById(@PathVariable ID entityId) {
        _checkBaseServiceNotNull();
        baseService.deleteById(entityId);
        return ResponseEntity.ok().build();
    }

    private UriList _createUriList(ID entityId) {
        String findAllUri = WebMvcLinkBuilder.linkTo(methodOn(getClass()).findAll(null, null)).toUri().toString();
        String createUri = WebMvcLinkBuilder.linkTo(methodOn(getClass()).create(null)).toUri().toString();
        String putUri = WebMvcLinkBuilder.linkTo(methodOn(getClass()).update(entityId, null)).toUri().toString();
        String deleteUri = WebMvcLinkBuilder.linkTo(methodOn(getClass()).deleteById(entityId)).toUri().toString();
        return new UriList(findAllUri, createUri, putUri, deleteUri);
    }

    private void _checkBaseServiceNotNull() {
        if (baseService == null) {
            throw new IllegalStateException("The baseService was not defined in the constructor");
        }
    }
}
