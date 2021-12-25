package com.invoice.core.application.invoice;

import com.invoice.core.domain.invoice.Invoice;
import com.invoice.core.domain.invoice.InvoiceBusinessLogic;
import com.invoice.core.domain.invoice.InvoiceRepository;
import com.invoice.core.domain.invoice.exception.InvoiceNotFoundException;
import com.invoice.core.infrastructure.spring_boot.web.common.ParamsItemDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class DefaultInvoiceService implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceBusinessLogic invoiceBusinessLogic;

    @Override
    public Invoice create(Invoice invoice) {
        this.invoiceBusinessLogic.createAndSetTrackNumber(invoice);
        invoice.setStatus(Invoice.InvoiceStatus.PENDING);
        invoice.setUuid(UUID.randomUUID());
        return invoiceRepository.create(invoice);
    }

    @Override
    public Invoice updateToPaid(UUID uuid, Invoice.InvoicePaymentMethod paymentMethod, String payerId, List<ParamsItemDto> params) {
        var invoice = this.invoiceRepository.findByUuid(uuid).orElseThrow(InvoiceNotFoundException::new);
        this.invoiceBusinessLogic.checkIsPending(invoice);
        invoice.setPaymentMethod(paymentMethod);
        invoice.setStatus(Invoice.InvoiceStatus.PAID);
        if (payerId != null) {
            invoice.setPayerId(payerId);
        }
        if (params != null) {
            for (var param : params) {
                if (param.getValue() != null) {
                    invoice.getParams().put(param.getKey(), param.getValue());
                }
            }
        }
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public Invoice updateToFailed(UUID uuid, String payerId, List<ParamsItemDto> params) {
        var invoice = this.invoiceRepository.findByUuid(uuid).orElseThrow(InvoiceNotFoundException::new);
        if (payerId != null) {
            invoice.setPayerId(payerId);
        }
        if (params != null) {
            for (var param : params) {
                if (param.getValue() != null) {
                    invoice.getParams().put(param.getKey(), param.getValue());
                }
            }
        }
        invoice.setStatus(Invoice.InvoiceStatus.FAILED);
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public Invoice cancel(UUID uuid) {
        var invoice = this.invoiceRepository.findByUuid(uuid).orElseThrow(InvoiceNotFoundException::new);
        this.invoiceBusinessLogic.checkIsPending(invoice);
        invoice.setStatus(Invoice.InvoiceStatus.CANCELED);
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public Invoice findByUuidAndCreatorId(UUID uuid, String creatorId) {
        return invoiceRepository.findByUuidAndCreatorId(uuid, creatorId).orElseThrow(InvoiceNotFoundException::new);
    }

    @Override
    public Invoice findByUuid(UUID uuid) {
        return this.invoiceRepository.findByUuid(uuid).orElseThrow(InvoiceNotFoundException::new);
    }

    @Override
    public Invoice update(Invoice invoice) {
        var foundedInvoice = this.invoiceRepository.findByUuid(invoice.getUuid()).orElseThrow(InvoiceNotFoundException::new);
        if (invoice.getPayerId() != null) {
            foundedInvoice.setPayerId(invoice.getPayerId());
        }
        if (invoice.getPaymentReference() != null) {
            foundedInvoice.setPaymentReference(invoice.getPaymentReference());
        }
        invoiceRepository.save(foundedInvoice);
        return foundedInvoice;
    }
}
