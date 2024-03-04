package com.devfelipe.transparencyportal.employee.resource.hateoas;

import com.devfelipe.transparencyportal.assignment.resource.AssignmentController;
import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateaosServiceImp;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.specification.EmployeeSpecification;
import com.devfelipe.transparencyportal.employee.resource.EmployeeController;
import com.devfelipe.transparencyportal.fundingsource.resource.FundingSourceController;
import com.devfelipe.transparencyportal.jobtitle.resource.JobTitleController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Service
public class EmployeeHateoasServiceImp extends BaseHateaosServiceImp<Integer, EmployeeResponseDto, EmployeeSpecification> implements EmployeeHateoasService {
    @Override
    public void addJoinLink(EmployeeResponseDto response) {
        response.setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).findById(response.getId())).withSelfRel().toUri());
        response.getJobTitle().setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(JobTitleController.class).findById(response.getJobTitle().getJobTitleId())).withSelfRel().toUri());
        response.getFundingSource().setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(FundingSourceController.class).findById(response.getFundingSource().getFundingSourceId())).withSelfRel().toUri());
        response.getAssignment().setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(AssignmentController.class).findById(response.getAssignment().getAssignmentId())).withSelfRel().toUri());
    }
}
