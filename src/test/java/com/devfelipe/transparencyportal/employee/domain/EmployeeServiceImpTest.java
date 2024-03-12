package com.devfelipe.transparencyportal.employee.domain;

import com.devfelipe.transparencyportal.assignment.domain.AssignmentService;
import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.employee.dto.EmployeeMapper;
import com.devfelipe.transparencyportal.employee.dto.EmployeeRequestDto;
import com.devfelipe.transparencyportal.employee.infra.EmployeeRepository;
import com.devfelipe.transparencyportal.fundingsource.domain.FundingSourceService;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.jobtitle.domain.JobTitleService;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImpTest {

    @Mock
    private JobTitleService jobTitleService;
    @Mock
    private FundingSourceService fundingSourceService;
    @Mock
    private AssignmentService assignmentService;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImp employeeServiceImp;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("Should create entity from Request DTO when everything is ok")
    void createEntityFromDtoCase1() {
        // Arrange
        JobTitle jobTitle = new JobTitle();
        jobTitle.setJobTitleId(TestConstants.BASE_INTEGER_ID);
        FundingSource fundingSource = new FundingSource();
        fundingSource.setFundingSourceId(TestConstants.BASE_INTEGER_ID);
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(TestConstants.BASE_INTEGER_ID);
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(jobTitle.getJobTitleId(), fundingSource.getFundingSourceId(), assignment.getAssignmentId());
        Employee employee = _createEmployeeFromRequestDTO(requestDto, jobTitle, fundingSource, assignment);

        when(employeeMapper.mapToEmployee(any(), any(), any(), any())).thenReturn(employee);

        // Act
        Employee newEmployee = employeeServiceImp._createEntityFromDto(requestDto);

        // Assert
        assertEquals(requestDto.name(), newEmployee.getName());
        assertEquals(requestDto.employmentType(), newEmployee.getEmploymentType());
        assertEquals(requestDto.employmentStartDate(), newEmployee.getEmploymentStartDate());
        assertEquals(requestDto.employmentEndDate(), newEmployee.getEmploymentEndDate());
        assertEquals(requestDto.weeklyWorkHours(), newEmployee.getWeeklyWorkHours());
        assertEquals(requestDto.jobTitleId(), newEmployee.getJobTitle().getJobTitleId());
        assertEquals(requestDto.fundingSourceId(), newEmployee.getFundingSource().getFundingSourceId());
        assertEquals(requestDto.assignmentId(), newEmployee.getAssignment().getAssignmentId());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newEmployee.getCreatedAt());
        assertEquals(TestConstants.BASE_INSTANT_LAST_UPDATE, newEmployee.getUpdatedAt());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when creating a Employee and there is no JobTitle with the specified ID")
    void createEntityFromDtoCase2() {
        // Arrange
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID,TestConstants.BASE_INTEGER_ID,TestConstants.BASE_INTEGER_ID);
        when(jobTitleService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);
        when(fundingSourceService.findByIdReturnsEntity(any())).thenReturn(new FundingSource());
        when(assignmentService.findByIdReturnsEntity(any())).thenReturn(new Assignment());

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
           employeeServiceImp._createEntityFromDto(requestDto);
        });
        verify(fundingSourceService, never()).findByIdReturnsEntity(any());
        verify(assignmentService, never()).findByIdReturnsEntity(any());
        verify(employeeMapper, never()).mapToEmployee(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when creating a Employee and there is no FundingSource with the specified ID")
    void createEntityFromDtoCase3() {
        // Arrange
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID,TestConstants.BASE_INTEGER_ID,TestConstants.BASE_INTEGER_ID);
        when(jobTitleService.findByIdReturnsEntity(any())).thenReturn(new JobTitle());
        when(fundingSourceService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);
        when(assignmentService.findByIdReturnsEntity(any())).thenReturn(new Assignment());

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImp._createEntityFromDto(requestDto);
        });
        verify(jobTitleService, times(1)).findByIdReturnsEntity(any());
        verify(assignmentService, never()).findByIdReturnsEntity(any());
        verify(employeeMapper, never()).mapToEmployee(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when creating a Employee and there is no Assignment with the specified ID")
    void createEntityFromDtoCase4() {
        // Arrange
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID,TestConstants.BASE_INTEGER_ID,TestConstants.BASE_INTEGER_ID);
        when(jobTitleService.findByIdReturnsEntity(any())).thenReturn(new JobTitle());
        when(fundingSourceService.findByIdReturnsEntity(any())).thenReturn(new FundingSource());
        when(assignmentService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImp._createEntityFromDto(requestDto);
        });
        verify(jobTitleService, times(1)).findByIdReturnsEntity(any());
        verify(fundingSourceService, times(1)).findByIdReturnsEntity(any());
        verify(employeeMapper, never()).mapToEmployee(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Should update entity from Request DTO when everything is ok")
    void updateEntityFromDtoCase1() {
        // Arrange
        Integer employeeId = TestConstants.BASE_INTEGER_ID;
        JobTitle jobTitle = new JobTitle();
        jobTitle.setJobTitleId(TestConstants.BASE_INTEGER_ID);
        FundingSource fundingSource = new FundingSource();
        fundingSource.setFundingSourceId(TestConstants.BASE_INTEGER_ID);
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(TestConstants.BASE_INTEGER_ID);
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(jobTitle.getJobTitleId(), fundingSource.getFundingSourceId(), assignment.getAssignmentId());
        Employee savedEmployee = _createEmployeeForUpdate(employeeId);

        when(employeeRepository.findById(any())).thenReturn(Optional.of(savedEmployee));
        when(jobTitleService.findByIdReturnsEntity(any())).thenReturn(jobTitle);
        when(fundingSourceService.findByIdReturnsEntity(any())).thenReturn(fundingSource);
        when(assignmentService.findByIdReturnsEntity(any())).thenReturn(assignment);

        // Act
        Employee newEmployee = employeeServiceImp._updateEntityFromDto(employeeId, requestDto);

        // Assert
        assertEquals(employeeId, newEmployee.getEmployeeId());
        assertEquals(requestDto.name(), newEmployee.getName());
        assertEquals(requestDto.employmentType(), newEmployee.getEmploymentType());
        assertEquals(requestDto.employmentStartDate(), newEmployee.getEmploymentStartDate());
        assertEquals(requestDto.employmentEndDate(), newEmployee.getEmploymentEndDate());
        assertEquals(requestDto.weeklyWorkHours(), newEmployee.getWeeklyWorkHours());
        assertEquals(requestDto.jobTitleId(), newEmployee.getJobTitle().getJobTitleId());
        assertEquals(requestDto.fundingSourceId(), newEmployee.getFundingSource().getFundingSourceId());
        assertEquals(requestDto.assignmentId(), newEmployee.getAssignment().getAssignmentId());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newEmployee.getCreatedAt());
        assertTrue(newEmployee.getUpdatedAt().isAfter(TestConstants.BASE_INSTANT_LAST_UPDATE));
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when updating a Employee and there is no Employee with the specified ID")
    void updateEntityFromDtoCase2() {
        // Arrange
        Integer employeeId = TestConstants.BASE_INTEGER_ID;
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID);

        when(jobTitleService.findByIdReturnsEntity(any())).thenReturn(new JobTitle());
        when(fundingSourceService.findByIdReturnsEntity(any())).thenReturn(new FundingSource());
        when(assignmentService.findByIdReturnsEntity(any())).thenReturn(new Assignment());

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImp._updateEntityFromDto(employeeId, requestDto);
        });

        verify(employeeRepository, times(1)).findById(any());
        verify(jobTitleService, never()).findByIdReturnsEntity(any());
        verify(fundingSourceService, never()).findByIdReturnsEntity(any());
        verify(assignmentService, never()).findByIdReturnsEntity(any());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when updating a Employee and there is no JobTitle with the specified ID")
    void updateEntityFromDtoCase3() {
        // Arrange
        Integer employeeId = TestConstants.BASE_INTEGER_ID;
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID);

        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee()));
        when(jobTitleService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);
        when(fundingSourceService.findByIdReturnsEntity(any())).thenReturn(new FundingSource());
        when(assignmentService.findByIdReturnsEntity(any())).thenReturn(new Assignment());

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImp._updateEntityFromDto(employeeId, requestDto);
        });

        verify(employeeRepository, times(1)).findById(any());
        verify(jobTitleService, times(1)).findByIdReturnsEntity(any());
        verify(fundingSourceService, never()).findByIdReturnsEntity(any());
        verify(assignmentService, never()).findByIdReturnsEntity(any());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when updating a Employee and there is no FundingSource with the specified ID")
    void updateEntityFromDtoCase4() {
        // Arrange
        Integer employeeId = TestConstants.BASE_INTEGER_ID;
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID);

        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee()));
        when(jobTitleService.findByIdReturnsEntity(any())).thenReturn(new JobTitle());
        when(fundingSourceService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);
        when(assignmentService.findByIdReturnsEntity(any())).thenReturn(new Assignment());

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImp._updateEntityFromDto(employeeId, requestDto);
        });

        verify(employeeRepository, times(1)).findById(any());
        verify(jobTitleService, times(1)).findByIdReturnsEntity(any());
        verify(fundingSourceService, times(1)).findByIdReturnsEntity(any());
        verify(assignmentService, never()).findByIdReturnsEntity(any());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when updating a Employee and there is no Assignment with the specified ID")
    void updateEntityFromDtoCase5() {
        // Arrange
        Integer employeeId = TestConstants.BASE_INTEGER_ID;
        EmployeeRequestDto requestDto = _createEmployeeRequestDto(TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID, TestConstants.BASE_INTEGER_ID);

        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee()));
        when(jobTitleService.findByIdReturnsEntity(any())).thenReturn(new JobTitle());
        when(fundingSourceService.findByIdReturnsEntity(any())).thenReturn(new FundingSource());
        when(assignmentService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImp._updateEntityFromDto(employeeId, requestDto);
        });

        verify(employeeRepository, times(1)).findById(any());
        verify(jobTitleService, times(1)).findByIdReturnsEntity(any());
        verify(fundingSourceService, times(1)).findByIdReturnsEntity(any());
        verify(assignmentService, times(1)).findByIdReturnsEntity(any());
    }

    @Test
    @DisplayName("Should throw an DataIntegrityViolationException on deletion if entity is referenced elsewhere.")
    void checkDataIntegrityViolationForDeletion() {
        // Arrange
        Integer employeeId = TestConstants.BASE_INTEGER_ID;
        Employee employee = new Employee();
        employee.getCompensations().add(new Compensation());

        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));

        // Act Assert
        assertThrows(DataIntegrityViolationException.class, () -> {
           employeeServiceImp._checkDataIntegrityViolationForDeletion(employeeId);
        });
    }


    private Employee _createEmployeeFromRequestDTO(EmployeeRequestDto requestDto, JobTitle jobTitle, FundingSource fundingSource, Assignment assignment ) {
        return Employee.builder()
                .name(requestDto.name())
                .employmentType(requestDto.employmentType())
                .employmentStartDate(requestDto.employmentStartDate())
                .employmentEndDate(requestDto.employmentEndDate())
                .weeklyWorkHours(requestDto.weeklyWorkHours())
                .jobTitle(jobTitle)
                .fundingSource(fundingSource)
                .assignment(assignment)
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .updatedAt(TestConstants.BASE_INSTANT_LAST_UPDATE)
                .build();
    }

    private Employee _createEmployeeForUpdate(Integer employeeId) {
        return Employee.builder()
                .employeeId(employeeId)
                .name(TestConstants.BASE_NAME_PRE_UPDATE)
                .employmentType(TestConstants.BASE_EMPLOYEE_TYPE_PRE_UPDATE)
                .employmentStartDate(TestConstants.BASE_START_DATE_PRE_UPDATE)
                .employmentEndDate(TestConstants.BASE_END_DATE_PRE_UPDATE)
                .weeklyWorkHours(TestConstants.BASE_WEEKLY_WORK_HOURS_PRE_UPDATE)
                .jobTitle(new JobTitle())
                .fundingSource(new FundingSource())
                .assignment(new Assignment())
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .updatedAt(TestConstants.BASE_INSTANT_LAST_UPDATE)
                .build();
    }

    private EmployeeRequestDto _createEmployeeRequestDto(Integer jobTitleId, Integer fundingSourceId, Integer assignmentId) {
        return new EmployeeRequestDto(
                TestConstants.BASE_NAME,
                TestConstants.BASE_EMPLOYEE_TYPE,
                TestConstants.BASE_START_DATE,
                TestConstants.BASE_END_DATE,
                TestConstants.BASE_WEEKLY_WORK_HOURS,
                jobTitleId,
                fundingSourceId,
                assignmentId
        );
    }
}