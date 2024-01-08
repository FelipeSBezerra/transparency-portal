package com.devfelipe.transparencyportal.compensation.domain;

import com.devfelipe.transparencyportal.common.domain.BaseService;
import com.devfelipe.transparencyportal.compensation.dto.CompensationRequestDto;
import com.devfelipe.transparencyportal.compensation.dto.CompensationResponseDto;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;

import java.util.UUID;

public interface CompensationService extends BaseService<Compensation, UUID, CompensationRequestDto, CompensationResponseDto> {
}
