package com.devfelipe.transparencyportal.assignment.resource.hateoas;

import com.devfelipe.transparencyportal.assignment.dto.AssignmentResponseDto;
import com.devfelipe.transparencyportal.assignment.infra.specification.AssignmentSpecification;
import com.devfelipe.transparencyportal.assignment.resource.AssignmentController;
import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateaosServiceImp;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Service
public class AssignmentHateoasServiceImp extends BaseHateaosServiceImp<Integer, AssignmentResponseDto, AssignmentSpecification> implements AssignmentHateoasService {
    @Override
    public void addJoinLink(AssignmentResponseDto response) {
        response.setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(AssignmentController.class).findById(response.getId())).withSelfRel().toUri());
    }
}
