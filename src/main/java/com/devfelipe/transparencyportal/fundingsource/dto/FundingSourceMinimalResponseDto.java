package com.devfelipe.transparencyportal.fundingsource.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMinimalResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FundingSourceMinimalResponseDto extends BaseMinimalResponseDto {

    private Integer fundingSourceId;
    private String fundingSourceName;
}
