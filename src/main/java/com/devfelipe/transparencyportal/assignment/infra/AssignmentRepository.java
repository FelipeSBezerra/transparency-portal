package com.devfelipe.transparencyportal.assignment.infra;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
