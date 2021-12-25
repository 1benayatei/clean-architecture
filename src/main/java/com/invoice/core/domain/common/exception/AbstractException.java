package com.invoice.core.domain.common.exception;

public abstract class AbstractException extends RuntimeException {
    public abstract String getStatus();

    public abstract int getCode();
}
