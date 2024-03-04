package com.devfelipe.transparencyportal.common.resource.hateoas;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.PagedModelWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.ResponseDtoWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.UriList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines a HATEOAS (Hypermedia as the Engine of Application State) service, providing functionality
 * to enhance responses with hypermedia links.
 *
 * @param <ID>   Type of the unique identifier used in the service.
 * @param <Resp> Type of the response DTO.
 * @param <S>    Type of the specification used in the service, if applicable.
 */
public interface BaseHateoasService<ID, Resp, S> {

    public ResponseDtoWithActions<ID> addActionsInFindById(BaseResponseDto<ID> baseResponseDto, UriList uriList);
    public PagedModelWithActions<Resp> addActionsInFindAll(Page<Resp> responseDtoPage, String createUri, S specification, Pageable pageable);
}
