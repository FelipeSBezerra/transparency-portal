package com.devfelipe.transparencyportal.assignment.domain;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentRequestDto;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentResponseDto;
import com.devfelipe.transparencyportal.assignment.infra.specification.AssignmentSpecification;
import com.devfelipe.transparencyportal.common.domain.BaseService;

public interface AssignmentService extends BaseService<Assignment, Integer, AssignmentRequestDto, AssignmentResponseDto, AssignmentSpecification> {
}
