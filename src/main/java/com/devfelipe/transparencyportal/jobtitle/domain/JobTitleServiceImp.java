package com.devfelipe.transparencyportal.jobtitle.domain;

import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleMapper;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleRequestDto;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;
import com.devfelipe.transparencyportal.jobtitle.infra.JobTitleRepository;
import org.springframework.stereotype.Service;

@Service
public class JobTitleServiceImp extends BaseServiceImp<JobTitle, Integer, JobTitleRequestDto, JobTitleResponseDto> implements JobTitleService{

    public JobTitleServiceImp(final JobTitleRepository repository, final JobTitleMapper jobTitleMapper) {
        super(JobTitle.class, repository, jobTitleMapper);
    }
}
