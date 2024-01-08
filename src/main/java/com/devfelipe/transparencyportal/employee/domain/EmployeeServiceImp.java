package com.devfelipe.transparencyportal.employee.domain;

import com.devfelipe.transparencyportal.assignment.domain.AssignmentService;
import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMapper;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.EmployeeRepository;
import com.devfelipe.transparencyportal.fundingsource.domain.FundingSourceService;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.jobtitle.domain.JobTitleService;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmployeeServiceImp extends BaseServiceImp<Employee, Integer, EmployeeRequestDto, EmployeeResponseDto> implements EmployeeService{

    private final JobTitleService jobTitleService;
    private final FundingSourceService fundingSourceService;
    private final AssignmentService assignmentService;
    private final EmployeeMapper employeeMapper;

    protected EmployeeServiceImp(final EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, JobTitleService jobTitleService, FundingSourceService fundingSourceService, AssignmentService assignmentService) {
        super(Employee.class, employeeRepository, employeeMapper);
        this.jobTitleService = jobTitleService;
        this.employeeMapper = employeeMapper;
        this.fundingSourceService = fundingSourceService;
        this.assignmentService = assignmentService;
    }

    @Override
    protected Employee _createEntityFromDto(EmployeeRequestDto employeeRequestDto) {
        JobTitle jobTitle = jobTitleService.findByIdReturnsEntity(employeeRequestDto.jobTitleId());
        FundingSource fundingSource = fundingSourceService.findByIdReturnsEntity(employeeRequestDto.fundingSourceId());
        Assignment assignment = assignmentService.findByIdReturnsEntity(employeeRequestDto.fundingSourceId());
        return employeeMapper.mapToEmployee(employeeRequestDto, jobTitle, fundingSource, assignment);
    }

    @Override
    protected Employee _updateEntityFromDto(Integer entityId, EmployeeRequestDto employeeRequestDto) {
        Employee savedEmployee = this.findByIdReturnsEntity(entityId);
        JobTitle jobTitle = jobTitleService.findByIdReturnsEntity(employeeRequestDto.jobTitleId());
        FundingSource fundingSource = fundingSourceService.findByIdReturnsEntity(employeeRequestDto.fundingSourceId());
        Assignment assignment = assignmentService.findByIdReturnsEntity(employeeRequestDto.fundingSourceId());
        _updateData(savedEmployee, employeeRequestDto, jobTitle, fundingSource, assignment);
        return savedEmployee;
    }

    @Override
    protected void _checkDataIntegrityViolationForDeletion(Integer entityId) {
        if (!this.findByIdReturnsEntity(entityId).getCompensations().isEmpty()) {
            throw new DataIntegrityViolationException(
                    String.format("The employee with id = %d has compensations related to him and therefore cannot be deleted", entityId));
        }
    }

    private void _updateData(Employee savedEmployee, EmployeeRequestDto employeeRequestDto, JobTitle jobTitle, FundingSource fundingSource, Assignment assignment) {
        savedEmployee.setName(employeeRequestDto.name());
        savedEmployee.setEmploymentType(employeeRequestDto.employmentType());
        savedEmployee.setEmploymentStartDate(employeeRequestDto.employmentStartDate());
        savedEmployee.setEmploymentEndDate(employeeRequestDto.employmentEndDate());
        savedEmployee.setWeeklyWorkHours(employeeRequestDto.weeklyWorkHours());
        savedEmployee.setJobTitle(jobTitle);
        savedEmployee.setFundingSource(fundingSource);
        savedEmployee.setAssignment(assignment);
        savedEmployee.setUpdatedAt(Instant.now());
    }
}
