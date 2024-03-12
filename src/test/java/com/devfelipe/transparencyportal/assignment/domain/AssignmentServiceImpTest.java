package com.devfelipe.transparencyportal.assignment.domain;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentMapper;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentRequestDto;
import com.devfelipe.transparencyportal.assignment.infra.AssignmentRepository;
import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AssignmentServiceImpTest {

    @Mock
    private AssignmentMapper assignmentMapper;
    @Mock
    private AssignmentRepository assignmentRepository;
    @InjectMocks
    private AssignmentServiceImp assignmentServiceImp;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create entity from Request DTO")
    void createEntityFromDto() {
        // Arrange
        AssignmentRequestDto requestDto = new AssignmentRequestDto(TestConstants.BASE_NAME);
        Assignment assignment = _createAssignmentFromRequestDTO(requestDto);

        when(assignmentMapper.mapToAssignment(requestDto)).thenReturn(assignment);

        // Act
        Assignment newAssignment = assignmentServiceImp._createEntityFromDto(requestDto);

        // Assert
        assertEquals(requestDto.name(), newAssignment.getName());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newAssignment.getCreatedAt());
    }

    @Test
    @DisplayName("Should update entity from Request DTO when everything is ok")
    void updateEntityFromDtoCase1() {
        // Arrange
        Integer assignmentId = TestConstants.BASE_INTEGER_ID;
        AssignmentRequestDto requestDto = new AssignmentRequestDto(TestConstants.BASE_NAME);
        Assignment savedAssignment = _createAssignmentForUpdate(assignmentId);

        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(savedAssignment));

        // Act
        Assignment updatedAssignment = assignmentServiceImp._updateEntityFromDto(assignmentId,requestDto);

        // Assert
        assertEquals(requestDto.name(), updatedAssignment.getName());
        assertTrue(updatedAssignment.getUpdatedAt().isAfter(TestConstants.BASE_INSTANT_LAST_UPDATE));
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when there is no Assignment with the specified id")
    void updateEntityFromDtoCase2() {
        // Arrange
        Integer assignmentId = TestConstants.BASE_INTEGER_ID;
        AssignmentRequestDto requestDto = new AssignmentRequestDto(TestConstants.BASE_NAME);
        String errorMessage = String.format("There is no resource of type %s with the id %d in the database.", Assignment.class.getSimpleName(), assignmentId);

        // Act Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            assignmentServiceImp._updateEntityFromDto(assignmentId,requestDto);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw an DataIntegrityViolationException on deletion if entity is referenced elsewhere.")
    void checkDataIntegrityViolationForDeletion() {
        // Arrange
        Integer assignmentId = TestConstants.BASE_INTEGER_ID;
        Employee employee = new Employee();
        Assignment assignment = new Assignment();
        assignment.getEmployees().add(employee);
        String errorMessage = String.format("The assignment with id = %d has employees related to him and therefore cannot be deleted", assignmentId);

        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));

        // Act Assert
        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
            assignmentServiceImp._checkDataIntegrityViolationForDeletion(assignmentId);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    private Assignment _createAssignmentFromRequestDTO(AssignmentRequestDto requestDto) {
        return Assignment.builder()
                .name(requestDto.name())
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .build();
    }

    private Assignment _createAssignmentForUpdate(Integer assignmentId) {
        return Assignment.builder()
                .assignmentId(assignmentId)
                .name(TestConstants.BASE_NAME_PRE_UPDATE)
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .updatedAt(TestConstants.BASE_INSTANT_LAST_UPDATE)
                .build();
    }
}