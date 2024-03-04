package com.devfelipe.transparencyportal.compensation.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateoasService;
import com.devfelipe.transparencyportal.common.resource.hateoas.specific.JoinLinkHateoasProvider;
import com.devfelipe.transparencyportal.compensation.dto.CompensationResponseDto;
import com.devfelipe.transparencyportal.compensation.infra.specification.CompensationSpecification;

import java.util.UUID;

public interface CompensationHateoasService extends BaseHateoasService<UUID, CompensationResponseDto, CompensationSpecification>, JoinLinkHateoasProvider<UUID ,CompensationResponseDto> {
}
