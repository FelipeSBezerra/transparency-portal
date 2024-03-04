package com.devfelipe.transparencyportal.fundingsource.resource.hateoas;

import com.devfelipe.transparencyportal.common.resource.hateoas.BaseHateaosServiceImp;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceResponseDto;
import com.devfelipe.transparencyportal.fundingsource.infra.specification.FundingSourceSpecification;
import com.devfelipe.transparencyportal.fundingsource.resource.FundingSourceController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Service
public class FundingSourceHateoasServiceImp extends BaseHateaosServiceImp<Integer, FundingSourceResponseDto, FundingSourceSpecification> implements FundingSourceHateoasService {
    @Override
    public void addJoinLink(FundingSourceResponseDto response) {
        response.setSelfLink(WebMvcLinkBuilder.linkTo(methodOn(FundingSourceController.class).findById(response.getId())).withSelfRel().toUri());
    }
}
