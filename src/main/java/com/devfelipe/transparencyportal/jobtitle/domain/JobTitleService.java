package com.devfelipe.transparencyportal.jobtitle.domain;

import com.devfelipe.transparencyportal.common.domain.BaseService;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleRequestDto;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;

public interface JobTitleService extends BaseService<JobTitle, Integer, JobTitleRequestDto, JobTitleResponseDto> {

}
