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

@Service
public class HateaosServiceImp<EntityIdType, ResponseDto, Specification> implements HateoasService<EntityIdType, ResponseDto, Specification> {

    @Override
    public ResponseDtoWithActions<EntityIdType> addActionsInFindById(BaseResponseDto<EntityIdType> baseResponseDto, UriList uriList) {
        List<Action> actions = List.of(
                new Action("all", uriList.findAllUri(), "GET"),
                new Action("update", uriList.putUri(), "PUT"),
                new Action("delete", uriList.putUri(), "DELETE")
        );
        return new ResponseDtoWithActions<>(baseResponseDto, actions);
    }

    @Override
    public PagedModelWithActions<ResponseDto> addActionsInFindAll(Page<ResponseDto> responseDtoPage, String createUri, Specification specification, Pageable pageable) {
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
