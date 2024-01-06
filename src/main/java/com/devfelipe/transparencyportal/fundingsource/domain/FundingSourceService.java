package com.devfelipe.transparencyportal.fundingsource.domain;

import com.devfelipe.transparencyportal.common.domain.BaseService;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceRequestDto;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceResponseDto;

public interface FundingSourceService extends BaseService<FundingSource, Integer, FundingSourceRequestDto, FundingSourceResponseDto> {
}
