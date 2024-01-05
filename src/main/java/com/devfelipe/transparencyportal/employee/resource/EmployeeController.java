package com.devfelipe.transparencyportal.employee.resource;

import com.devfelipe.transparencyportal.common.resource.BaseController;
import com.devfelipe.transparencyportal.employee.domain.EmployeeService;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController extends BaseController<Integer, EmployeeRequestDto, EmployeeResponseDto> {

    protected EmployeeController(final EmployeeService employeeService) {
        super(employeeService);
    }
}
