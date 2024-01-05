package com.devfelipe.transparencyportal.common.resource;

import com.devfelipe.transparencyportal.common.domain.BaseService;
import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public abstract class BaseController<EntityIdType, RequestDto, ResponseDto extends BaseResponseDto> {

    private final BaseService<EntityIdType, RequestDto, ResponseDto> baseService;

    protected BaseController(BaseService<EntityIdType, RequestDto, ResponseDto> baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    public ResponseEntity<Page<ResponseDto>> findAll(Pageable pageable) {
        _ckeckBaseServiceNotNull();
        return ResponseEntity.ok(baseService.findAll(pageable));
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto> findById(@PathVariable EntityIdType entityId) {
        _ckeckBaseServiceNotNull();
        return ResponseEntity.ok(baseService.findById(entityId));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid RequestDto requestDto) {
        _ckeckBaseServiceNotNull();
        ResponseDto savedEntity = baseService.create(requestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{entityId}").buildAndExpand(savedEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{entityId}")
    public ResponseEntity<ResponseDto> update(@PathVariable EntityIdType entityId, @RequestBody @Valid RequestDto requestDto) {
        _ckeckBaseServiceNotNull();
        return ResponseEntity.ok(baseService.update(entityId, requestDto));
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<Void> deleteById(@PathVariable EntityIdType entityId) {
        _ckeckBaseServiceNotNull();
        baseService.deleteById(entityId);
        return ResponseEntity.ok().build();
    }

    private void _ckeckBaseServiceNotNull() {
        if (baseService == null) {
            throw new IllegalStateException("The baseService was not defined in the constructor");
        }
    }
}
