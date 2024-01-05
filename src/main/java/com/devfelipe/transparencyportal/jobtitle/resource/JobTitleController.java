package com.devfelipe.transparencyportal.jobtitle.resource;

import com.devfelipe.transparencyportal.common.resource.BaseController;
import com.devfelipe.transparencyportal.jobtitle.domain.JobTitleService;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleRequestDto;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobtitle")
public class JobTitleController extends BaseController<Integer, JobTitleRequestDto, JobTitleResponseDto> {

    public JobTitleController(final JobTitleService jobTitleService) {
        super(jobTitleService);
    }
}
