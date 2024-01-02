package com.devfelipe.transparencyportal.common.infra;

import com.devfelipe.transparencyportal.common.domain.exception.BadRequestException;
import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardErrorMessage> badRequestException(BadRequestException exception, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage errorMessage = StandardErrorMessage.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Bad Request")
                .message(exception.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorMessage> resourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorMessage errorMessage = StandardErrorMessage.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Resource Not Found")
                .message(exception.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(errorMessage);
    }
}
