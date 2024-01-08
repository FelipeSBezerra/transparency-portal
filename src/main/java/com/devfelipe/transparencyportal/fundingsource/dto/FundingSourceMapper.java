package com.devfelipe.transparencyportal.fundingsource.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FundingSourceMapper implements BaseMapper<FundingSource, FundingSourceResponseDto, FundingSourceRequestDto> {

    @Override
    public FundingSourceResponseDto mapToResponseDto(FundingSource fundingSource) {
        return new  FundingSourceResponseDto(
                fundingSource.getFundingSourceId(),
                fundingSource.getName(),
                fundingSource.getCreatedAt(),
                fundingSource.getUpdatedAt()
        );
    }

    public FundingSource mapToFundingSource(FundingSourceRequestDto fundingSourceRequestDto) {
        return FundingSource.builder()
                .name(fundingSourceRequestDto.name())
                .createdAt(Instant.now())
                .build();
    }

    public FundingSourceMinimalResponseDto mapToMinimalResponseDto(FundingSource fundingSource) {
        return new FundingSourceMinimalResponseDto(
                fundingSource.getFundingSourceId(),
                fundingSource.getName()
        );
    }
}
