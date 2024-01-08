package com.devfelipe.transparencyportal.compensation.dto;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMinimalResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.util.UUID;

public record CompensationResponseDto(
        UUID id,
        EmployeeMinimalResponseDto employee,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
        YearMonth compensationReferenceYearMonth,

        BigDecimal baseSalary,
        BigDecimal permanentAllowances,
        BigDecimal temporaryAllowances,
        BigDecimal vacationPay,
        BigDecimal indemnityBenefits,
        BigDecimal legalDeductions,
        BigDecimal miscellaneousDeduction,
        BigDecimal totalDeductions,
        BigDecimal totalGrossCompensation,
        BigDecimal totalNetCompensation,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant updatedAt

) implements BaseResponseDto<UUID> {

    @Override
    public UUID getId() {
        return this.id;
    }
}
