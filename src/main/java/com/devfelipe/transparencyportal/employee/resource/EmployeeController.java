package com.devfelipe.transparencyportal.employee.resource;

import com.devfelipe.transparencyportal.common.resource.BaseController;
import com.devfelipe.transparencyportal.employee.domain.EmployeeService;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.specification.EmployeeSpecification;
import com.devfelipe.transparencyportal.employee.resource.hateoas.EmployeeHateoasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController extends BaseController<Employee, Integer, EmployeeRequestDto, EmployeeResponseDto, EmployeeSpecification> {

    protected EmployeeController(final EmployeeService employeeService, final EmployeeHateoasService employeeHateoasService) {
        super(employeeService, employeeHateoasService);
    }
}
