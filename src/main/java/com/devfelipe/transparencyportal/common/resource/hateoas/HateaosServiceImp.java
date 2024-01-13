package com.devfelipe.transparencyportal.common.resource.hateoas;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.PagedModelWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.ResponseDtoWithActions;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.Action;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.UriList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the HATEOAS (Hypermedia as the Engine of Application State) service, providing methods to enhance
 * responses with hypermedia links.
 *
 * @param <ID>   Type of the unique identifier used in the service.
 * @param <Resp> Type of the response DTO.
 * @param <S>    Type of the specification used in the service, if applicable.
 */
@Service
public class HateaosServiceImp<ID, Resp, S> implements HateoasService<ID, Resp, S> {

    @Override
    public ResponseDtoWithActions<ID> addActionsInFindById(BaseResponseDto<ID> baseResponseDto, UriList uriList) {
        List<Action> actions = List.of(
                new Action("all", uriList.findAllUri(), "GET"),
                new Action("update", uriList.putUri(), "PUT"),
                new Action("delete", uriList.putUri(), "DELETE")
        );
        return new ResponseDtoWithActions<>(baseResponseDto, actions);
    }

    @Override
    public PagedModelWithActions<Resp> addActionsInFindAll(Page<Resp> responseDtoPage, String createUri, S specification, Pageable pageable) {
        List<Action> actions = List.of(
                new Action("create", createUri, "POST")
        );

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                responseDtoPage.getSize(),
                responseDtoPage.getNumber(),
                responseDtoPage.getTotalElements(),
                responseDtoPage.getTotalPages());

        return new PagedModelWithActions(responseDtoPage.getContent(), actions, metadata);
    }
}
