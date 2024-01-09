package com.devfelipe.transparencyportal.common.resource.exceptionhandler.errormessage;

import com.devfelipe.transparencyportal.common.resource.exceptionhandler.errormessage.field.FieldMessage;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationErrorMessage extends StandardErrorMessage{

    private final List<FieldMessage> fieldErrors = new ArrayList<>();

    @Builder(builderMethodName = "ValidationErrorMessageBuilder")
    public ValidationErrorMessage(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addError(FieldMessage fieldMessage) {
        fieldErrors.add(fieldMessage);
    }
}
