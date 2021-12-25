package com.invoice.core.infrastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public final class ErrorDto {
    private Error error;

    public ErrorDto(String message, String status, Integer code) {
        this.error = new Error(code, message, status);
    }

    @Setter
    @Getter
    @RequiredArgsConstructor
    private static class Error {
        private final Integer code;
        private final String message;
        private final String status;
    }
}
