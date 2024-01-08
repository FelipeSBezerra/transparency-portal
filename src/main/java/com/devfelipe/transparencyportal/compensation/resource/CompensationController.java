package com.devfelipe.transparencyportal.compensation.resource;

import com.devfelipe.transparencyportal.common.resource.BaseController;
import com.devfelipe.transparencyportal.compensation.domain.CompensationService;
import com.devfelipe.transparencyportal.compensation.dto.CompensationRequestDto;
import com.devfelipe.transparencyportal.compensation.dto.CompensationResponseDto;
import com.devfelipe.transparencyportal.compensation.domain.model.Compensation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("compensation")
public class CompensationController extends BaseController<Compensation, UUID, CompensationRequestDto, CompensationResponseDto> {
    protected CompensationController(final CompensationService compensationService) {
        super(compensationService);
    }
}
