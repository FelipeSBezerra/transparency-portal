package com.devfelipe.transparencyportal.jobtitle.infra;

import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
}
