package com.invoice.core.infrastructure.spring_boot.web.invoice.dto;

import com.invoice.core.domain.invoice.Invoice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
public class InvoiceUpdateDto {

    @Nullable
    private String payerId;

    @Nullable
    private String paymentReference;

    public Invoice toInvoice() {
        var invoice = new Invoice();
        invoice.setPaymentReference(paymentReference);
        invoice.setPayerId(payerId);

        return invoice;
    }
}
