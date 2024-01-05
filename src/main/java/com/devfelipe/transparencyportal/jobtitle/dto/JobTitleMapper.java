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

    @Override
    public JobTitle mapToEntityClass(JobTitleRequestDto jobTitleRequestDto) {
        return JobTitle.builder()
                .name(jobTitleRequestDto.name())
                .build();
    }

    @Override
    public JobTitle updateFromDto(JobTitle entity, JobTitleRequestDto jobTitleRequestDto) {
        entity.setName(jobTitleRequestDto.name());
        entity.setUpdatedAt(Instant.now());
        return entity;
    }

}
