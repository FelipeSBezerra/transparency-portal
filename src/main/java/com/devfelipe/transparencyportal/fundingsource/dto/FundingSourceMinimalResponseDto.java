package com.devfelipe.transparencyportal.fundingsource.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundingSourceMinimalResponseDto {

    private Integer fundingSourceId;
    private String fundingSourceName;
}
