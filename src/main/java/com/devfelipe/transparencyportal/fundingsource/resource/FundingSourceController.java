package com.devfelipe.transparencyportal.fundingsource.resource;

import com.devfelipe.transparencyportal.common.resource.BaseController;
import com.devfelipe.transparencyportal.fundingsource.domain.FundingSourceService;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceRequestDto;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceResponseDto;
import com.devfelipe.transparencyportal.fundingsource.infra.specification.FundingSourceSpecification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fundingsource")
public class FundingSourceController extends BaseController<FundingSource, Integer, FundingSourceRequestDto, FundingSourceResponseDto, FundingSourceSpecification> {
    protected FundingSourceController(final FundingSourceService fundingSourceService) {
        super(fundingSourceService);
    }
}
