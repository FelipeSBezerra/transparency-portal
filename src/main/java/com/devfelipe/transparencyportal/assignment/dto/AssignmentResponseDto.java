package com.devfelipe.transparencyportal.assignment.dto;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record AssignmentResponseDto(
        Integer id,
        String name,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant updatedAt
) implements BaseResponseDto<Integer> {

    @Override
    public Integer getId() {
        return this.id;
    }
}
