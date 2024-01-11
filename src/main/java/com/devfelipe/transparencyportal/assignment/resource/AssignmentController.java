package com.devfelipe.transparencyportal.assignment.resource;

import com.devfelipe.transparencyportal.assignment.domain.AssignmentService;
import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentRequestDto;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentResponseDto;
import com.devfelipe.transparencyportal.assignment.infra.specification.AssignmentSpecification;
import com.devfelipe.transparencyportal.common.resource.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assignment")
public class AssignmentController extends BaseController<Assignment, Integer, AssignmentRequestDto, AssignmentResponseDto, AssignmentSpecification> {
    protected AssignmentController(final AssignmentService assignmentService) {
        super(assignmentService);
    }
}
