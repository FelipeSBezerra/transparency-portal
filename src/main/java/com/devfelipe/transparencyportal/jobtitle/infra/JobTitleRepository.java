package com.devfelipe.transparencyportal.jobtitle.infra;

import com.devfelipe.transparencyportal.common.infra.BaseRepository;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends BaseRepository<JobTitle, Integer> {
}
