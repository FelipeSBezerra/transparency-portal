package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMinimalResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmployeeMinimalResponseDto extends BaseMinimalResponseDto {

    private Integer employeeId;
    private String employeeName;
}
