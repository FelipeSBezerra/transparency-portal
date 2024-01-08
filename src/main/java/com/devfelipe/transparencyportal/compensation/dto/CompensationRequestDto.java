package com.devfelipe.transparencyportal.compensation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.YearMonth;

public record CompensationRequestDto(

        @NotNull(message = "The \"employeeId\" field cannot be empty")
        Integer employeeId,

        @NotNull(message = "The \"name\" field cannot be empty")
        @JsonFormat(pattern = "yyyy-MM")
        YearMonth compensationReferenceYearMonth,

        @NotNull(message = "The \"baseSalary\" field cannot be empty")
        BigDecimal baseSalary,

        @NotNull(message = "The \"temporaryAllowances\" field cannot be empty")
        BigDecimal permanentAllowances,

        @NotNull(message = "The \"temporaryAllowances\" field cannot be empty")
        BigDecimal temporaryAllowances,

        @NotNull(message = "The \"vacationPay\" field cannot be empty")
        BigDecimal vacationPay,

        @NotNull(message = "The \"indemnityBenefits\" field cannot be empty")
        BigDecimal indemnityBenefits,

        @NotNull(message = "The \"legalDeductions\" field cannot be empty")
        BigDecimal legalDeductions,

        @NotNull(message = "The \"miscellaneousDeduction\" field cannot be empty")
        BigDecimal miscellaneousDeduction
) {
}
