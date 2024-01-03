package com.devfelipe.transparencyportal.employee;

import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;

public class EmployeeMapper {

    public static EmployeeResponseDto mapToResponseDto(Employee employee) {
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

    public static Employee mapToEmployee(EmployeeRequestDto employeeRequestDto) {
        return Employee.builder()
                .name(employeeRequestDto.name())
                .employmentType(employeeRequestDto.employmentType())
                .employmentStartDate(employeeRequestDto.employmentStartDate())
                .employmentEndDate(employeeRequestDto.employmentEndDate())
                .weeklyWorkHours(employeeRequestDto.weeklyWorkHours())
                .build();
    }
}
