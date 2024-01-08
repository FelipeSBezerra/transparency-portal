package com.devfelipe.transparencyportal.compensation.domain;

import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.compensation.dto.CompensationMapper;
import com.devfelipe.transparencyportal.compensation.dto.CompensationRequestDto;
import com.devfelipe.transparencyportal.compensation.dto.CompensationResponseDto;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import com.devfelipe.transparencyportal.compensation.infra.CompensationRepository;
import com.devfelipe.transparencyportal.employee.domain.EmployeeService;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompensationServiceImp extends BaseServiceImp<Compensation, UUID, CompensationRequestDto, CompensationResponseDto> implements CompensationService {

    private final CompensationMapper compensationMapper;
    private final EmployeeService employeeService;

    protected CompensationServiceImp(final CompensationRepository compensationRepository, CompensationMapper compensationMapper, EmployeeService employeeService) {
        super(Compensation.class, compensationRepository, compensationMapper);
        this.compensationMapper = compensationMapper;
        this.employeeService = employeeService;
    }

    @Override
    protected Compensation _createEntityFromDto(CompensationRequestDto compensationRequestDto) {
        Employee employee = employeeService.findByIdReturnsEntity(compensationRequestDto.employeeId());
        return compensationMapper.mapToCompensation(compensationRequestDto, employee);
    }

    @Override
    protected Compensation _updateEntityFromDto(UUID entityId, CompensationRequestDto compensationRequestDto) {
        Compensation savedCompensation = this.findByIdReturnsEntity(entityId);
        Employee employee = employeeService.findByIdReturnsEntity(compensationRequestDto.employeeId());
        _updateData(savedCompensation, compensationRequestDto, employee);
        return savedCompensation;
    }

    @Override
    protected void _checkDataIntegrityViolationForDeletion(UUID entityId) {
    }

    private void _updateData(Compensation savedCompensation, CompensationRequestDto compensationRequestDto,Employee employee) {
        savedCompensation.setEmployee(employee);
        savedCompensation.setCompensationReferenceYearMonth(compensationRequestDto.compensationReferenceYearMonth());
        savedCompensation.setBaseSalary(compensationRequestDto.baseSalary());
        savedCompensation.setPermanentAllowances(compensationRequestDto.permanentAllowances());
        savedCompensation.setTemporaryAllowances(compensationRequestDto.temporaryAllowances());
        savedCompensation.setVacationPay(compensationRequestDto.vacationPay());
        savedCompensation.setIndemnityBenefits(compensationRequestDto.indemnityBenefits());
        savedCompensation.setLegalDeductions(compensationRequestDto.legalDeductions());
        savedCompensation.setMiscellaneousDeduction(compensationRequestDto.miscellaneousDeduction());
    }
}
