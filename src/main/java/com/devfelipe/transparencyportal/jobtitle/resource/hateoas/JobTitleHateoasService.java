package com.devfelipe.transparencyportal.jobtitle.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateoasService;
import com.devfelipe.transparencyportal.common.resource.hateoas.specific.JoinLinkHateoasProvider;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;
import com.devfelipe.transparencyportal.jobtitle.infra.specification.JobTitleSpecification;

public interface JobTitleHateoasService extends BaseHateoasService<Integer, JobTitleResponseDto, JobTitleSpecification>, JoinLinkHateoasProvider<Integer, JobTitleResponseDto> {
}
