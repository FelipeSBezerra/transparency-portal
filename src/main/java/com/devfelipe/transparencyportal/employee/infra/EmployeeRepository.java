package com.devfelipe.transparencyportal.employee.infra;

import com.devfelipe.transparencyportal.common.infra.BaseRepository;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {
}
