package com.api.mushroom.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomValidationException extends RuntimeException {
    private Map<String, String> errorMap;

    public CustomValidationException(String fieldName, String errorMessage) {
        this.errorMap = new HashMap<>();
        this.errorMap.put(fieldName, errorMessage);
    }

    public Map<String, String> getError() {
        return errorMap;
    }
}
