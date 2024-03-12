package com.devfelipe.transparencyportal.compensation.domain;

import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import com.devfelipe.transparencyportal.compensation.dto.CompensationMapper;
import com.devfelipe.transparencyportal.compensation.dto.CompensationRequestDto;
import com.devfelipe.transparencyportal.compensation.infra.CompensationRepository;
import com.devfelipe.transparencyportal.employee.domain.EmployeeService;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CompensationServiceImpTest {

    @Mock
    private CompensationMapper compensationMapper;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private CompensationRepository compensationRepository;
    @InjectMocks
    private CompensationServiceImp compensationServiceImp;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create entity from Request DTO when everything is ok")
    void createEntityFromDtoCase1() {
        // Arrange
        Employee employee = new Employee();
        employee.setEmployeeId(TestConstants.BASE_INTEGER_ID);
        CompensationRequestDto requestDto = _createCompensationRequestDTO(employee.getEmployeeId());
        Compensation compensation = _createCompensationFromRequestDTO(requestDto, employee);

        when(employeeService.findByIdReturnsEntity(requestDto.employeeId())).thenReturn(employee);
        when(compensationMapper.mapToCompensation(requestDto, employee)).thenReturn(compensation);

        // Act
        Compensation newCompensation = compensationServiceImp._createEntityFromDto(requestDto);

        // Assert
        assertEquals(requestDto.employeeId(), newCompensation.getEmployee().getEmployeeId());
        assertEquals(requestDto.compensationReferenceYearMonth(), newCompensation.getCompensationReferenceYearMonth());
        assertEquals(requestDto.baseSalary(), newCompensation.getBaseSalary());
        assertEquals(requestDto.permanentAllowances(), newCompensation.getPermanentAllowances());
        assertEquals(requestDto.temporaryAllowances(), newCompensation.getTemporaryAllowances());
        assertEquals(requestDto.vacationPay(), newCompensation.getVacationPay());
        assertEquals(requestDto.indemnityBenefits(), newCompensation.getIndemnityBenefits());
        assertEquals(requestDto.legalDeductions(), newCompensation.getLegalDeductions());
        assertEquals(requestDto.miscellaneousDeduction(), newCompensation.getMiscellaneousDeduction());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newCompensation.getCreatedAt());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when there is no Employee with the specified id")
    void createEntityFromDtoCase2() {
        // Arrange
        when(employeeService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);

        // Act Assert
        assertThrows(ResourceNotFoundException.class, () -> {
           employeeService.findByIdReturnsEntity(1);
        });
    }

    @Test
    @DisplayName("Should update entity from Request DTO when everything is ok")
    void updateEntityFromDtoCase1() {
        // Arrange
        UUID compensationId = UUID.randomUUID();
        Employee employee = new Employee();
        employee.setEmployeeId(TestConstants.BASE_INTEGER_ID);
        CompensationRequestDto requestDto = _createCompensationRequestDTO(employee.getEmployeeId());
        Compensation savedCompensation = _createCompensationForUpdate(compensationId);

        when(compensationRepository.findById(any())).thenReturn(Optional.of(savedCompensation));
        when(employeeService.findByIdReturnsEntity(any())).thenReturn(employee);

        // Act
        Compensation newCompensation = compensationServiceImp._updateEntityFromDto(compensationId, requestDto);

        // Assert
        assertEquals(compensationId, newCompensation.getCompensationId());
        assertEquals(requestDto.employeeId(), newCompensation.getEmployee().getEmployeeId());
        assertEquals(requestDto.compensationReferenceYearMonth(), newCompensation.getCompensationReferenceYearMonth());
        assertEquals(requestDto.baseSalary(), newCompensation.getBaseSalary());
        assertEquals(requestDto.permanentAllowances(), newCompensation.getPermanentAllowances());
        assertEquals(requestDto.temporaryAllowances(), newCompensation.getTemporaryAllowances());
        assertEquals(requestDto.vacationPay(), newCompensation.getVacationPay());
        assertEquals(requestDto.indemnityBenefits(), newCompensation.getIndemnityBenefits());
        assertEquals(requestDto.legalDeductions(), newCompensation.getLegalDeductions());
        assertEquals(requestDto.miscellaneousDeduction(), newCompensation.getMiscellaneousDeduction());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newCompensation.getCreatedAt());
        assertTrue(newCompensation.getUpdatedAt().isAfter(TestConstants.BASE_INSTANT_LAST_UPDATE));
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when there is no Compensation with the specified id")
    void updateEntityFromDtoCase2() {
        // Arrange
        UUID compensationId = UUID.randomUUID();
        Employee employee = new Employee();
        CompensationRequestDto requestDto = _createCompensationRequestDTO(TestConstants.BASE_INTEGER_ID);

        String errorMessage = String.format("There is no resource of type %s with the id %s in the database.", Compensation.class.getSimpleName(), compensationId.toString());

        when(employeeService.findByIdReturnsEntity(any())).thenReturn(employee);

        // Act Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
           compensationServiceImp._updateEntityFromDto(compensationId, requestDto);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when there is no Employee with the specified id")
    void updateEntityFromDtoCase3() {
        // Arrange
        UUID compensationId = UUID.randomUUID();
        Employee employee = new Employee();
        CompensationRequestDto requestDto = _createCompensationRequestDTO(TestConstants.BASE_INTEGER_ID);
        Compensation savedCompensation = _createCompensationFromRequestDTO(requestDto, employee);

        when(compensationRepository.findById(compensationId)).thenReturn(Optional.of(savedCompensation));
        when(employeeService.findByIdReturnsEntity(any())).thenThrow(ResourceNotFoundException.class);

        // Act Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            compensationServiceImp._updateEntityFromDto(compensationId, requestDto);
        });
    }

    private Compensation _createCompensationFromRequestDTO(CompensationRequestDto requestDto, Employee employee) {
        return Compensation.builder()
                .employee(employee)
                .compensationReferenceYearMonth(requestDto.compensationReferenceYearMonth())
                .baseSalary(requestDto.baseSalary())
                .permanentAllowances(requestDto.permanentAllowances())
                .temporaryAllowances(requestDto.temporaryAllowances())
                .vacationPay(requestDto.vacationPay())
                .indemnityBenefits(requestDto.indemnityBenefits())
                .legalDeductions(requestDto.legalDeductions())
                .miscellaneousDeduction(requestDto.miscellaneousDeduction())
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .build();
    }

    private Compensation _createCompensationForUpdate(UUID compensationId) {
        return Compensation.builder()
                .compensationId(compensationId)
                .employee(new Employee())
                .compensationReferenceYearMonth(TestConstants.BASE_YEAR_MONTH_PRE_UPDATE)
                .baseSalary(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .permanentAllowances(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .temporaryAllowances(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .vacationPay(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .indemnityBenefits(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .legalDeductions(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .miscellaneousDeduction(TestConstants.BASE_BIG_DECIMAL_VALUE_PRE_UPDATE)
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .updatedAt(TestConstants.BASE_INSTANT_LAST_UPDATE)
                .build();
    }

    private CompensationRequestDto _createCompensationRequestDTO(Integer employeeId) {
        return new CompensationRequestDto(
                employeeId,
                TestConstants.BASE_YEAR_MONTH,
                TestConstants.BASE_BIG_DECIMAL_VALUE,
                TestConstants.BASE_BIG_DECIMAL_VALUE,
                TestConstants.BASE_BIG_DECIMAL_VALUE,
                TestConstants.BASE_BIG_DECIMAL_VALUE,
                TestConstants.BASE_BIG_DECIMAL_VALUE,
                TestConstants.BASE_BIG_DECIMAL_VALUE,
                TestConstants.BASE_BIG_DECIMAL_VALUE
        );
    }
}