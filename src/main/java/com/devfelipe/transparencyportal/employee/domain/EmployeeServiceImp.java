package com.devfelipe.transparencyportal.employee.domain;

import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMapper;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp extends BaseServiceImp<Employee, Integer, EmployeeRequestDto, EmployeeResponseDto> implements EmployeeService{

    protected EmployeeServiceImp(final EmployeeRepository repository, final EmployeeMapper employeeMapper) {
        super(Employee.class, repository, employeeMapper);
    }
}
