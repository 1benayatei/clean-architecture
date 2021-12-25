package com.invoice.core.domain.common.exception;

public abstract class AbstractNotFoundException extends AbstractException {
    @Override
    public int getCode() {
        return 404;
    }
}
