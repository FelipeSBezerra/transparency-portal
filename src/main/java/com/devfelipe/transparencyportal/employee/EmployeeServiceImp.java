package com.devfelipe.transparencyportal.employee;

import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import com.devfelipe.transparencyportal.employee.infra.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<EmployeeResponseDto> findAll(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<EmployeeResponseDto> employeeResponseDtoList = employeePage.getContent().stream()
                .map(EmployeeMapper::mapToResponseDto)
                .collect(Collectors.toList());

        return new PageImpl<>(employeeResponseDtoList, employeePage.getPageable(), employeePage.getTotalElements());
    }


    @Override
    public EmployeeResponseDto findById(Integer employeeId) {
        return EmployeeMapper.mapToResponseDto(_findById(employeeId));
    }


    @Override
    public EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto) {

        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeRequestDto);
        newEmployee.setCreatedAt(Instant.now());

        Employee savedEmployee = employeeRepository.save(newEmployee);

        return EmployeeMapper.mapToResponseDto(savedEmployee);
    }

    @Override
    public EmployeeResponseDto update(Integer employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee savadEmployee = _findById(employeeId);
        Employee updatedEmployee = _updateEmployeeData(savadEmployee, employeeRequestDto);
        return EmployeeMapper.mapToResponseDto(employeeRepository.save(updatedEmployee));
    }

    @Override
    public void deleteById(Integer employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException(
                    String.format("There is no resource of type Employee with the id %d in the database.", employeeId));
        }
        employeeRepository.deleteById(employeeId);
    }

    private Employee _findById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(
                String.format("There is no resource of type Employee with the id %d in the database.", employeeId)));
    }

    private Employee _updateEmployeeData(Employee savadEmployee, EmployeeRequestDto employeeRequestDto) {
        savadEmployee.setName(employeeRequestDto.name());
        savadEmployee.setEmploymentType(employeeRequestDto.employmentType());
        savadEmployee.setEmploymentStartDate(employeeRequestDto.employmentStartDate());
        savadEmployee.setEmploymentEndDate(employeeRequestDto.employmentEndDate());
        savadEmployee.setWeeklyWorkHours(employeeRequestDto.weeklyWorkHours());
        savadEmployee.setUpdatedAt(Instant.now());

        return savadEmployee;
    }
}
