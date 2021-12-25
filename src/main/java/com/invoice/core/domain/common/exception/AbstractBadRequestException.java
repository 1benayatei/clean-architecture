package com.invoice.core.domain.common.exception;

public abstract class AbstractBadRequestException extends AbstractException {
    @Override
    public int getCode() {
        return 400;
    }
}
