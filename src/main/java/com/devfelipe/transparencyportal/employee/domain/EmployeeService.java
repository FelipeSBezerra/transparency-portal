package com.devfelipe.transparencyportal.employee.domain;

import com.devfelipe.transparencyportal.common.domain.BaseService;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;

public interface EmployeeService extends BaseService<Employee, Integer, EmployeeRequestDto, EmployeeResponseDto> {

}
