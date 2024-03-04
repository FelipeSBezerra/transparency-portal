package com.devfelipe.transparencyportal.jobtitle.resource;

import com.devfelipe.transparencyportal.common.resource.BaseController;
import com.devfelipe.transparencyportal.jobtitle.domain.JobTitleService;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleRequestDto;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;
import com.devfelipe.transparencyportal.jobtitle.infra.specification.JobTitleSpecification;
import com.devfelipe.transparencyportal.jobtitle.resource.hateoas.JobTitleHateoasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobtitle")
public class JobTitleController extends BaseController<JobTitle, Integer, JobTitleRequestDto, JobTitleResponseDto, JobTitleSpecification> {

    public JobTitleController(final JobTitleService jobTitleService, final JobTitleHateoasService jobTitleHateoasService) {
        super(jobTitleService, jobTitleHateoasService);
    }
}
