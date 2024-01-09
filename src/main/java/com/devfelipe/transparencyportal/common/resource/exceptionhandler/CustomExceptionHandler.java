package com.devfelipe.transparencyportal.common.resource.exceptionhandler;

import com.devfelipe.transparencyportal.common.domain.exception.BadRequestException;
import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import com.devfelipe.transparencyportal.common.domain.exception.ResourceNotFoundException;
import com.devfelipe.transparencyportal.common.resource.exceptionhandler.errormessage.field.FieldMessage;
import com.devfelipe.transparencyportal.common.resource.exceptionhandler.errormessage.StandardErrorMessage;
import com.devfelipe.transparencyportal.common.resource.exceptionhandler.errormessage.ValidationErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardErrorMessage> dataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardErrorMessage errorMessage = StandardErrorMessage.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Data Integrity Violation")
                .message(exception.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErrorMessage errorMessage = ValidationErrorMessage.ValidationErrorMessageBuilder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Method Argument Not Valid")
                .message("Error validating fields")
                .path(httpServletRequest.getRequestURI())
                .build();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                errorMessage.addError(new FieldMessage(fieldError.getField(), error.getDefaultMessage()));
            }
        });
        return ResponseEntity.status(status).body(errorMessage);
    }
}
