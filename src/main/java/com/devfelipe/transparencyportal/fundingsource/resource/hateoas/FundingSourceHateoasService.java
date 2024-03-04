package com.devfelipe.transparencyportal.fundingsource.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateoasService;
import com.devfelipe.transparencyportal.common.resource.hateoas.specific.JoinLinkHateoasProvider;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceResponseDto;
import com.devfelipe.transparencyportal.fundingsource.infra.specification.FundingSourceSpecification;

public interface FundingSourceHateoasService extends BaseHateoasService<Integer, FundingSourceResponseDto, FundingSourceSpecification>, JoinLinkHateoasProvider<Integer, FundingSourceResponseDto> {
}
