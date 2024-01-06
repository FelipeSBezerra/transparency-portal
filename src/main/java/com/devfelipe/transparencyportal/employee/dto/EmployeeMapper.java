package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EmployeeMapper implements BaseMapper<Employee, EmployeeResponseDto, EmployeeRequestDto> {

    @Override
    public EmployeeResponseDto mapToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmploymentType(),
                employee.getEmploymentStartDate(),
                employee.getEmploymentEndDate(),
                employee.getWeeklyWorkHours(),
                employee.getJobTitle(),
                employee.getFundingSource(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    public Employee mapToEmployee(EmployeeRequestDto employeeRequestDto, JobTitle jobTitle, FundingSource fundingSource) {
        return Employee.builder()
                .name(employeeRequestDto.name())
                .employmentType(employeeRequestDto.employmentType())
                .employmentStartDate(employeeRequestDto.employmentStartDate())
                .employmentEndDate(employeeRequestDto.employmentEndDate())
                .weeklyWorkHours(employeeRequestDto.weeklyWorkHours())
                .jobTitle(jobTitle)
                .fundingSource(fundingSource)
                .createdAt(Instant.now())
                .build();
    }
}
