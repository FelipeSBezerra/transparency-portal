package com.devfelipe.transparencyportal.assignment.infra;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.common.infra.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends BaseRepository<Assignment, Integer> {
}
