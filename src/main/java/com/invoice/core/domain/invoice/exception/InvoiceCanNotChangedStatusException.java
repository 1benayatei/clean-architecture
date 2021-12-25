package com.invoice.core.domain.invoice.exception;

import com.invoice.core.domain.common.exception.AbstractBadRequestException;

public class InvoiceCanNotChangedStatusException extends AbstractBadRequestException {
    @Override
    public String getStatus() {
        return "INVOICE_CAN_NOT_CHANGED_STATUS";
    }
}
