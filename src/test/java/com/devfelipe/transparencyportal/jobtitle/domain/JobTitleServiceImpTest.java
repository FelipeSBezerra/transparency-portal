package com.devfelipe.transparencyportal.jobtitle.domain;

import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleMapper;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleRequestDto;
import com.devfelipe.transparencyportal.jobtitle.infra.JobTitleRepository;
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

class JobTitleServiceImpTest {

    @Mock
    private JobTitleMapper jobTitleMapper;
    @Mock
    private JobTitleRepository jobTitleRepository;
    @InjectMocks
    private JobTitleServiceImp jobTitleServiceImp;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create entity from Request DTO")
    void createEntityFromDto() {
        // Arrange
        JobTitleRequestDto requestDto = new JobTitleRequestDto(TestConstants.BASE_NAME);
        JobTitle jobTitle = _createJobTitleFromRequestDTO(requestDto);

        when(jobTitleMapper.mapToJobTitle(requestDto)).thenReturn(jobTitle);

        // Act
        JobTitle newJobTitle = jobTitleServiceImp._createEntityFromDto(requestDto);

        // Assert
        assertEquals(requestDto.name(), newJobTitle.getName());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newJobTitle.getCreatedAt());
    }

    @Test
    @DisplayName("Should update entity from Request DTO when everything is ok")
    void updateEntityFromDtoCase1() {
        // Arrange
        Integer jobTitleId = TestConstants.BASE_INTEGER_ID;
        JobTitleRequestDto requestDto = new JobTitleRequestDto(TestConstants.BASE_NAME);
        JobTitle savedJobTitle = _createJobTitleForUpdate(jobTitleId);

        when(jobTitleRepository.findById(jobTitleId)).thenReturn(Optional.of(savedJobTitle));

        // Act
        JobTitle updatedJobTitle = jobTitleServiceImp._updateEntityFromDto(jobTitleId, requestDto);

        // Assert
        assertEquals(requestDto.name(), updatedJobTitle.getName());
        assertTrue(updatedJobTitle.getUpdatedAt().isAfter(TestConstants.BASE_INSTANT_LAST_UPDATE));
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when there is no JobTitle with the specified id")
    void updateEntityFromDtoCase2() {
        // Arrange
        Integer jobTitleId = TestConstants.BASE_INTEGER_ID;
        JobTitleRequestDto requestDto = new JobTitleRequestDto(TestConstants.BASE_NAME);
        String errorMessage = String.format("There is no resource of type %s with the id %d in the database.", JobTitle.class.getSimpleName(), jobTitleId);

        // Act Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,() -> {
           jobTitleServiceImp._updateEntityFromDto(jobTitleId, requestDto);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw an DataIntegrityViolationException on deletion if entity is referenced elsewhere.")
    void checkDataIntegrityViolationForDeletion() {
        // Arrange
        Integer jobTitleId = TestConstants.BASE_INTEGER_ID;
        Employee employee = new Employee();
        JobTitle jobTitle = new JobTitle();
        jobTitle.getEmployees().add(employee);
        String errorMessage = String.format("The job title with id = %d has employees related to him and therefore cannot be deleted", jobTitleId);

        when(jobTitleRepository.findById(jobTitleId)).thenReturn(Optional.of(jobTitle));

        // Act Assert
        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
            jobTitleServiceImp._checkDataIntegrityViolationForDeletion(jobTitleId);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    private JobTitle _createJobTitleFromRequestDTO(JobTitleRequestDto requestDto) {
        return JobTitle.builder()
                .name(requestDto.name())
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .build();
    }

    private JobTitle _createJobTitleForUpdate(Integer jobTitleId) {
        return JobTitle.builder()
                .jobTitleId(jobTitleId)
                .name(TestConstants.BASE_NAME_PRE_UPDATE)
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .updatedAt(TestConstants.BASE_INSTANT_LAST_UPDATE)
                .build();
    }
}