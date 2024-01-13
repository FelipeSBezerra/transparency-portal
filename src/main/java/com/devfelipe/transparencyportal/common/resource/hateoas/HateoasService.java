package com.devfelipe.transparencyportal.common.resource.hateoas;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.PagedModelWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.ResponseDtoWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.UriList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HateoasService<EntityIdType, ResponseDto, Specification> {

    public ResponseDtoWithActions<EntityIdType> addActionsInFindById(BaseResponseDto<EntityIdType> baseResponseDto, UriList uriList);
    public PagedModelWithActions<ResponseDto> addActionsInFindAll(Page<ResponseDto> responseDtoPage, String createUri, Specification specification, Pageable pageable);
}
