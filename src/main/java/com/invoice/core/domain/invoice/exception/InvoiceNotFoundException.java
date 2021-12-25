package com.invoice.core.domain.invoice.exception;

import com.invoice.core.domain.common.exception.AbstractNotFoundException;

public class InvoiceNotFoundException extends AbstractNotFoundException {
    @Override
    public String getStatus() {
        return "INVOICE_NOT_FOUND";
    }
}
