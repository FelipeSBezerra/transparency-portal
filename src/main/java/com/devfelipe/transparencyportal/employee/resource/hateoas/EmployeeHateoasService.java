package com.devfelipe.transparencyportal.employee.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateoasService;
import com.devfelipe.transparencyportal.common.resource.hateoas.specific.JoinLinkHateoasProvider;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.specification.EmployeeSpecification;

public interface EmployeeHateoasService extends BaseHateoasService<Integer, EmployeeResponseDto, EmployeeSpecification>, JoinLinkHateoasProvider<Integer, EmployeeResponseDto> {
}
