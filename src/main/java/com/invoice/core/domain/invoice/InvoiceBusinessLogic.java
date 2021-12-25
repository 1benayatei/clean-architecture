package com.invoice.core.domain.invoice;

import com.invoice.core.domain.invoice.exception.InvoiceCanNotChangedStatusException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class InvoiceBusinessLogic {
    private final InvoiceRepository invoiceRepository;

    public void createAndSetTrackNumber(Invoice invoice) {
        var trackingCode = "";
        do {
            trackingCode = TrackingCodeGenerator.make();
        } while (this.invoiceRepository.findByTrackingCode(trackingCode).isPresent());
        invoice.setTrackingCode(trackingCode);
    }

    public void checkIsPending(Invoice invoice) {
        if (!invoice.getStatus().equals(Invoice.InvoiceStatus.PENDING)) {
            throw new InvoiceCanNotChangedStatusException();
        }
    }
}
