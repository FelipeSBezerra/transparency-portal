package com.devfelipe.transparencyportal.jobtitle.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMinimalResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class JobTitleMinimalResponseDto extends BaseMinimalResponseDto {

    private Integer jobTitleId;
    private String jobTitleName;
}
