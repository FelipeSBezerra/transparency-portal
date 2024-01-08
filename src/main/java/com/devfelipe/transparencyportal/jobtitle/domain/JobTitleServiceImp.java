package com.devfelipe.transparencyportal.jobtitle.domain;

import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleMapper;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleRequestDto;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;
import com.devfelipe.transparencyportal.jobtitle.infra.JobTitleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JobTitleServiceImp extends BaseServiceImp<JobTitle, Integer, JobTitleRequestDto, JobTitleResponseDto> implements JobTitleService{

    private final JobTitleMapper jobTitleMapper;

    public JobTitleServiceImp(final JobTitleRepository repository, JobTitleMapper jobTitleMapper) {
        super(JobTitle.class, repository, jobTitleMapper);
        this.jobTitleMapper = jobTitleMapper;
    }

    @Override
    protected JobTitle _createEntityFromDto(JobTitleRequestDto jobTitleRequestDto) {
        return jobTitleMapper.mapToJobTitle(jobTitleRequestDto);
    }

    @Override
    protected JobTitle _updateEntityFromDto(Integer entityId, JobTitleRequestDto jobTitleRequestDto) {
        JobTitle savedJobTitle = this.findByIdReturnsEntity(entityId);
        _updateData(savedJobTitle, jobTitleRequestDto);
        return savedJobTitle;
    }

    @Override
    protected void _checkDataIntegrityViolationForDeletion(Integer entityId) {
        if (!this.findByIdReturnsEntity(entityId).getEmployees().isEmpty()) {
            throw new DataIntegrityViolationException(
                    String.format("The job title with id = %d has employees related to him and therefore cannot be deleted", entityId));
        }
    }

    private void _updateData(JobTitle savedJobTitle, JobTitleRequestDto jobTitleRequestDto) {
        savedJobTitle.setName(jobTitleRequestDto.name());
        savedJobTitle.setUpdatedAt(Instant.now());
    }
}
