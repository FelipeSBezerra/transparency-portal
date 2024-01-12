package com.devfelipe.transparencyportal.compensation.dto;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMinimalResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class CompensationResponseDto extends BaseResponseDto<UUID> {

    private UUID id;
    private EmployeeMinimalResponseDto employee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
    private YearMonth compensationReferenceYearMonth;

    private BigDecimal baseSalary;
    private BigDecimal permanentAllowances;
    private BigDecimal temporaryAllowances;
    private BigDecimal vacationPay;
    private BigDecimal indemnityBenefits;
    private BigDecimal legalDeductions;
    private BigDecimal miscellaneousDeduction;
    private BigDecimal totalDeductions;
    private BigDecimal totalGrossCompensation;
    private BigDecimal totalNetCompensation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant updatedA;

}
