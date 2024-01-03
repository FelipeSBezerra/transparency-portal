package com.devfelipe.transparencyportal.employee.resource;

import com.devfelipe.transparencyportal.employee.EmployeeService;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.dto.EmployeeResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAll(pageable));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto savedEmployee = employeeService.create(employeeRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{employeeId}").buildAndExpand(savedEmployee.employeeId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable Integer employeeId, @RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.ok(employeeService.update(employeeId, employeeRequestDto));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer employeeId) {
        employeeService.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }
}
