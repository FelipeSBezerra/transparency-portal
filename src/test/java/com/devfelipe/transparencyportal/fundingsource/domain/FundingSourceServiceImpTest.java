package com.devfelipe.transparencyportal.fundingsource.domain;

import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.devfelipe.transparencyportal.fundingsource.domain.model.FundingSource;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceMapper;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceRequestDto;
import com.devfelipe.transparencyportal.fundingsource.infra.FundingSourceRepository;
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

class FundingSourceServiceImpTest {

    @Mock
    private FundingSourceMapper fundingSourceMapper;
    @Mock
    private FundingSourceRepository fundingSourceRepository;
    @InjectMocks
    private FundingSourceServiceImp fundingSourceServiceImp;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create entity from Request DTO")
    void createEntityFromDto() {
        // Arrange
        FundingSourceRequestDto requestDto = new FundingSourceRequestDto(TestConstants.BASE_NAME);
        FundingSource fundingSource = _createFundingSourceFromRequestDTO(requestDto);

        when(fundingSourceMapper.mapToFundingSource(requestDto)).thenReturn(fundingSource);

        // Act
        FundingSource newFundingSource = fundingSourceServiceImp._createEntityFromDto(requestDto);

        // Assert
        assertEquals(requestDto.name(), newFundingSource.getName());
        assertEquals(TestConstants.BASE_INSTANT_CREATE, newFundingSource.getCreatedAt());
    }

    @Test
    @DisplayName("Should update entity from Request DTO when everything is ok")
    void updateEntityFromDtoCase1() {
        // Arrange
        Integer fundingSourceId = TestConstants.BASE_INTEGER_ID;
        FundingSourceRequestDto requestDto = new FundingSourceRequestDto(TestConstants.BASE_NAME);
        FundingSource savedFundingSource = _createFundingSourceForUpdate(fundingSourceId);

        when(fundingSourceRepository.findById(fundingSourceId)).thenReturn(Optional.of(savedFundingSource));

        // Act
        FundingSource updatedFundingSource = fundingSourceServiceImp._updateEntityFromDto(fundingSourceId, requestDto);

        // Assert
        assertEquals(requestDto.name(), updatedFundingSource.getName());
        assertTrue(updatedFundingSource.getUpdatedAt().isAfter(TestConstants.BASE_INSTANT_LAST_UPDATE));
    }

    @Test
    @DisplayName("Should throw an ResourceNotFoundException when there is no Assignment with the specified id")
    void updateEntityFromDtoCase2() {
        // Arrange
        Integer fundingSourceId = TestConstants.BASE_INTEGER_ID;
        FundingSourceRequestDto requestDto = new FundingSourceRequestDto(TestConstants.BASE_NAME);
        String errorMessage = String.format("There is no resource of type %s with the id %d in the database.", FundingSource.class.getSimpleName(), fundingSourceId);

        // Act Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            fundingSourceServiceImp._updateEntityFromDto(fundingSourceId, requestDto);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw an DataIntegrityViolationException on deletion if entity is referenced elsewhere.")
    void checkDataIntegrityViolationForDeletion() {
        // Arrange
        Integer fundingSourceId = TestConstants.BASE_INTEGER_ID;
        Employee employee = new Employee();
        FundingSource fundingSource = new FundingSource();
        fundingSource.getEmployees().add(employee);
        String errorMessage = String.format("The funding source with id = %d has employees related to him and therefore cannot be deleted", fundingSourceId);

        when(fundingSourceRepository.findById(fundingSourceId)).thenReturn(Optional.of(fundingSource));

        // Act Assert
        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
           fundingSourceServiceImp._checkDataIntegrityViolationForDeletion(fundingSourceId);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    private FundingSource _createFundingSourceFromRequestDTO(FundingSourceRequestDto requestDto) {
        return FundingSource.builder()
                .name(requestDto.name())
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .build();
    }

    private FundingSource _createFundingSourceForUpdate(Integer fundingSourceId) {
        return FundingSource.builder()
                .fundingSourceId(fundingSourceId)
                .name(TestConstants.BASE_NAME_PRE_UPDATE)
                .createdAt(TestConstants.BASE_INSTANT_CREATE)
                .updatedAt(TestConstants.BASE_INSTANT_LAST_UPDATE)
                .build();
    }
}