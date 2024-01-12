package com.devfelipe.transparencyportal.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeMinimalResponseDto {

    private Integer employeeId;
    private String employeeName;
}
