package com.devfelipe.transparencyportal.common.resource.hateoas.response;

import com.devfelipe.transparencyportal.common.resource.hateoas.response.field.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.PagedModel;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PagedModelWithActions<T> {
    private List<T> result;
    private List<Action> actions;
    private PagedModel.PageMetadata page;
}
