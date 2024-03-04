package com.devfelipe.transparencyportal.compensation.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateaosServiceImp;
import com.devfelipe.transparencyportal.compensation.dto.CompensationResponseDto;
import com.devfelipe.transparencyportal.compensation.infra.specification.CompensationSpecification;
import com.devfelipe.transparencyportal.compensation.resource.CompensationController;
import com.devfelipe.transparencyportal.employee.resource.EmployeeController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Service
public class CompensationHateoasServiceImp extends BaseHateaosServiceImp<UUID, CompensationResponseDto, CompensationSpecification> implements CompensationHateoasService {
    @Override
    public void addJoinLink(CompensationResponseDto response) {
        response.setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(CompensationController.class).findById(response.getId())).withSelfRel().toUri());
        response.getEmployee().setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).findById(response.getEmployee().getEmployeeId())).withSelfRel().toUri());
    }
}
