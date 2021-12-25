package com.invoice.core.infrastructure.spring_boot.db.invoice.jpa;

import com.invoice.core.domain.invoice.Invoice;
import com.invoice.core.domain.invoice.InvoiceRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class JpaInvoiceRepository implements InvoiceRepository {

    private final InvoiceJpaRepository invoiceJpaRepository;

    @Override
    public Invoice create(Invoice invoice) {
        return save(invoice);
    }

    @Override
    public Invoice save(Invoice invoice) {
        var invoiceEntity = new InvoiceEntity();
        invoiceEntity.setId(invoice.getId());
        invoiceEntity.setUuid(invoice.getUuid());
        invoiceEntity.setAmount(invoice.getAmount());
        invoiceEntity.setPaymentMethod(invoice.getPaymentMethod());
        invoiceEntity.setPaymentReferenceId(invoice.getPaymentReference());
        invoiceEntity.setCreatorId(invoice.getCreatorId());
        invoiceEntity.setPayerId(invoice.getPayerId());
        invoiceEntity.setTrackingCode(invoice.getTrackingCode());
        invoiceEntity.setParams(invoice.getParams());
        invoiceEntity.setStatus(invoice.getStatus());
        invoiceEntity.setDescription(invoice.getDescription());
        invoiceEntity.setUpdatedAt(LocalDateTime.now());
        var result = invoiceJpaRepository.save(invoiceEntity);
        return invoiceEntityToInvoice(result);
    }

    @Override
    public Optional<Invoice> findByTrackingCode(String trackingCode) {
        return this.invoiceJpaRepository.findByTrackingCode(trackingCode)
                .map(this::invoiceEntityToInvoice);
    }

    @Override
    public Optional<Invoice> findByUuid(UUID uuid) {
        return this.invoiceJpaRepository.findByUuid(uuid).
                map(this::invoiceEntityToInvoice);
    }

    @Override
    public Optional<Invoice> findByUuidAndCreatorId(UUID uuid, String creatorId) {
        return this.invoiceJpaRepository.findByUuidAndCreatorId(uuid, creatorId)
                .map(this::invoiceEntityToInvoice);
    }

    private Invoice invoiceEntityToInvoice(InvoiceEntity invoiceEntity) {
        var result = new Invoice();
        result.setId(invoiceEntity.getId());
        result.setAmount(invoiceEntity.getAmount());
        result.setDescription(invoiceEntity.getDescription());
        result.setCreatorId(invoiceEntity.getCreatorId());
        result.setTrackingCode(invoiceEntity.getTrackingCode());
        result.setPaymentMethod(invoiceEntity.getPaymentMethod());
        result.setPaymentReference(invoiceEntity.getPaymentReferenceId());
        result.setPayerId(invoiceEntity.getPayerId());
        result.setParams(invoiceEntity.getParams());
        result.setStatus(invoiceEntity.getStatus());
        result.setId(invoiceEntity.getId());
        result.setCreatedAt(invoiceEntity.getCreatedAt());
        result.setUpdatedAt(invoiceEntity.getUpdatedAt());
        result.setUuid(invoiceEntity.getUuid());
        return result;
    }
}
