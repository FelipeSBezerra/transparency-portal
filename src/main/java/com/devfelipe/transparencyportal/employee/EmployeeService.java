package com.devfelipe.transparencyportal.employee;

import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {


    Page<EmployeeResponseDto> findAll(Pageable pageable);
    EmployeeResponseDto findById(Integer employeeId);
    EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto update(Integer employeeId, EmployeeRequestDto employeeRequestDto);
    void deleteById(Integer employeeId);
}
