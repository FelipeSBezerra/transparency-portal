package com.devfelipe.transparencyportal.assignment.resource.hateoas;

import com.devfelipe.transparencyportal.assignment.dto.AssignmentResponseDto;
import com.devfelipe.transparencyportal.assignment.infra.specification.AssignmentSpecification;
import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateoasService;
import com.devfelipe.transparencyportal.common.resource.hateoas.specific.JoinLinkHateoasProvider;

public interface AssignmentHateoasService extends BaseHateoasService<Integer, AssignmentResponseDto, AssignmentSpecification>, JoinLinkHateoasProvider<Integer, AssignmentResponseDto> {
}
