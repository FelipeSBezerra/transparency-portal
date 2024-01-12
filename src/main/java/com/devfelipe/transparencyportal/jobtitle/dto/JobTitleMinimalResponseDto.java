package com.devfelipe.transparencyportal.jobtitle.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobTitleMinimalResponseDto {

    private Integer jobTitleId;
    private String jobTitleName;
}
