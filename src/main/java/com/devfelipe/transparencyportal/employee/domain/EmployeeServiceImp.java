package com.devfelipe.transparencyportal.employee.domain;

import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMapper;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.EmployeeRepository;
import com.devfelipe.transparencyportal.jobtitle.domain.JobTitleService;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmployeeServiceImp extends BaseServiceImp<Employee, Integer, EmployeeRequestDto, EmployeeResponseDto> implements EmployeeService{

    private final JobTitleService jobTitleService;
    private final EmployeeMapper employeeMapper;

    protected EmployeeServiceImp(final EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, JobTitleService jobTitleService) {
        super(Employee.class, employeeRepository, employeeMapper);
        this.jobTitleService = jobTitleService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    protected Employee _createEntityFromDto(EmployeeRequestDto employeeRequestDto) {
        JobTitle jobTitle = jobTitleService.findByIdReturnsEntity(employeeRequestDto.jobTitleId());
        return employeeMapper.mapToEmployee(employeeRequestDto, jobTitle);
    }

    @Override
    protected Employee _updateEntityFromDto(Integer entityId, EmployeeRequestDto employeeRequestDto) {
        Employee savedEmployee = this.findByIdReturnsEntity(entityId);
        JobTitle jobTitle = jobTitleService.findByIdReturnsEntity(employeeRequestDto.jobTitleId());
        _updateData(savedEmployee, employeeRequestDto, jobTitle);
        return savedEmployee;
    }

    private void _updateData(Employee savedEmployee, EmployeeRequestDto employeeRequestDto, JobTitle jobTitle) {
        savedEmployee.setName(employeeRequestDto.name());
        savedEmployee.setEmploymentType(employeeRequestDto.employmentType());
        savedEmployee.setEmploymentStartDate(employeeRequestDto.employmentStartDate());
        savedEmployee.setEmploymentEndDate(employeeRequestDto.employmentEndDate());
        savedEmployee.setWeeklyWorkHours(employeeRequestDto.weeklyWorkHours());
        savedEmployee.setJobTitle(jobTitle);
        savedEmployee.setUpdatedAt(Instant.now());
    }
}
