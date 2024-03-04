package com.devfelipe.transparencyportal.jobtitle.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateaosServiceImp;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleResponseDto;
import com.devfelipe.transparencyportal.jobtitle.infra.specification.JobTitleSpecification;
import com.devfelipe.transparencyportal.jobtitle.resource.JobTitleController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Service
public class JobTitleHateoasServiceImp extends BaseHateaosServiceImp<Integer, JobTitleResponseDto, JobTitleSpecification> implements JobTitleHateoasService {
    @Override
    public void addJoinLink(JobTitleResponseDto response) {
        response.setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(JobTitleController.class).findById(response.getId())).withSelfRel().toUri());
    }
}
