package com.devfelipe.transparencyportal.jobtitle.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JobTitleMapper implements BaseMapper<JobTitle, JobTitleResponseDto, JobTitleRequestDto> {


    @Override
    public JobTitleResponseDto mapToResponseDto(JobTitle jobTitle) {
        return new JobTitleResponseDto(
                jobTitle.getJobTitleId(),
                jobTitle.getName(),
                jobTitle.getCreatedAt(),
                jobTitle.getUpdatedAt()
        );
    }

    public JobTitle mapToJobTitle(JobTitleRequestDto jobTitleRequestDto) {
        return JobTitle.builder()
                .name(jobTitleRequestDto.name())
                .createdAt(Instant.now())
                .build();
    }

    public JobTitleMinimalResponseDto mapToMinimalResponseDto(JobTitle jobTitle) {
        return new JobTitleMinimalResponseDto(
                jobTitle.getJobTitleId(),
                jobTitle.getName()
        );
    }

}
