package com.devfelipe.transparencyportal.compensation.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMinimalResponseDto;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CompensationMapper implements BaseMapper<Compensation, CompensationResponseDto, CompensationRequestDto> {

    @Override
    public CompensationResponseDto mapToResponseDto(Compensation compensation) {
        return new CompensationResponseDto(
                compensation.getCompensationId(),
                new EmployeeMinimalResponseDto(compensation.getEmployee().getEmployeeId(), compensation.getEmployee().getName()),
                compensation.getCompensationReferenceYearMonth(),
                compensation.getBaseSalary(),
                compensation.getPermanentAllowances(),
                compensation.getTemporaryAllowances(),
                compensation.getVacationPay(),
                compensation.getIndemnityBenefits(),
                compensation.getLegalDeductions(),
                compensation.getMiscellaneousDeduction(),
                compensation.getTotalDeductions(),
                compensation.getTotalGrossCompensation(),
                compensation.getTotalNetCompensation(),
                compensation.getCreatedAt(),
                compensation.getUpdatedAt()
        );
    }

    public Compensation mapToCompensation(CompensationRequestDto compensationRequestDto, Employee employee) {
        return Compensation.builder()
                .employee(employee)
                .compensationReferenceYearMonth(compensationRequestDto.compensationReferenceYearMonth())
                .baseSalary(compensationRequestDto.baseSalary())
                .permanentAllowances(compensationRequestDto.permanentAllowances())
                .temporaryAllowances(compensationRequestDto.temporaryAllowances())
                .vacationPay(compensationRequestDto.vacationPay())
                .indemnityBenefits(compensationRequestDto.indemnityBenefits())
                .legalDeductions(compensationRequestDto.legalDeductions())
                .miscellaneousDeduction(compensationRequestDto.miscellaneousDeduction())
                .createdAt(Instant.now())
                .build();
    }
}
