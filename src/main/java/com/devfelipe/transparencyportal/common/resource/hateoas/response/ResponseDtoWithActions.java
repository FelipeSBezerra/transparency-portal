package com.devfelipe.transparencyportal.common.resource.hateoas.response;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class ResponseDtoWithActions<EntityIdType> extends CollectionModel<ResponseDtoWithActions<EntityIdType>> {

    private BaseResponseDto<EntityIdType> result;
    private List<Action> actions;
}
