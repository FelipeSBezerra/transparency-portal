package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentMapper;
import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceMapper;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EmployeeMapper implements BaseMapper<Employee, EmployeeResponseDto> {

    private final JobTitleMapper jobTitleMapper;
    private final FundingSourceMapper fundingSourceMapper;
    private final AssignmentMapper assignmentMapper;

    public EmployeeMapper(JobTitleMapper jobTitleMapper, FundingSourceMapper fundingSourceMapper, AssignmentMapper assignmentMapper) {
        this.jobTitleMapper = jobTitleMapper;
        this.fundingSourceMapper = fundingSourceMapper;
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public EmployeeResponseDto mapToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmploymentType(),
                employee.getEmploymentStartDate(),
                employee.getEmploymentEndDate(),
                employee.getWeeklyWorkHours(),
                jobTitleMapper.mapToMinimalResponseDto(employee.getJobTitle()),
                fundingSourceMapper.mapToMinimalResponseDto(employee.getFundingSource()),
                assignmentMapper.mapToMinimalResponseDto(employee.getAssignment()),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    public Employee mapToEmployee(EmployeeRequestDto employeeRequestDto, JobTitle jobTitle, FundingSource fundingSource, Assignment assignment) {
        return Employee.builder()
                .name(employeeRequestDto.name())
                .employmentType(employeeRequestDto.employmentType())
                .employmentStartDate(employeeRequestDto.employmentStartDate())
                .employmentEndDate(employeeRequestDto.employmentEndDate())
                .weeklyWorkHours(employeeRequestDto.weeklyWorkHours())
                .jobTitle(jobTitle)
                .fundingSource(fundingSource)
                .assignment(assignment)
                .createdAt(Instant.now())
                .build();
    }

    public EmployeeMinimalResponseDto mapToMinimalResponseDto(Employee employee) {
        return new EmployeeMinimalResponseDto(
                employee.getEmployeeId(),
                employee.getName()
        );
    }
}
