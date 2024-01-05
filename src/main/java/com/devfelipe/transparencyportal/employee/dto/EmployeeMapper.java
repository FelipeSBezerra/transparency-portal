package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EmployeeMapper implements BaseMapper<Employee, EmployeeResponseDto, EmployeeRequestDto> {

    public EmployeeResponseDto mapToResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmploymentType(),
                employee.getEmploymentStartDate(),
                employee.getEmploymentEndDate(),
                employee.getWeeklyWorkHours(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    @Override
    public Employee mapToEntityClass(EmployeeRequestDto employeeRequestDto) {
        return Employee.builder()
                .name(employeeRequestDto.name())
                .employmentType(employeeRequestDto.employmentType())
                .employmentStartDate(employeeRequestDto.employmentStartDate())
                .employmentEndDate(employeeRequestDto.employmentEndDate())
                .weeklyWorkHours(employeeRequestDto.weeklyWorkHours())
                .build();
    }

    @Override
    public Employee updateFromDto(Employee entity, EmployeeRequestDto employeeRequestDto) {
        entity.setName(employeeRequestDto.name());
        entity.setEmploymentType(employeeRequestDto.employmentType());
        entity.setEmploymentStartDate(employeeRequestDto.employmentStartDate());
        entity.setEmploymentEndDate(employeeRequestDto.employmentEndDate());
        entity.setWeeklyWorkHours(employeeRequestDto.weeklyWorkHours());
        entity.setUpdatedAt(Instant.now());
        return entity;
    }

}
