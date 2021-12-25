package com.invoice.core.infrastructure.spring_boot.web.invoice.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.invoice.core.domain.invoice.Invoice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Builder
public class InvoiceDto {
    private UUID id;
    private int amount;
    private Invoice.InvoiceStatus status;
    private String trackingCode;
    private String creatorId;
    private String payerId;
    private Invoice.InvoicePaymentMethod paymentMethod;
    private String paymentReference;
    private String description;
    private Map<String, JsonNode> params;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static InvoiceDto fromInvoice(Invoice invoice) {
        return InvoiceDto.builder()
                .id(invoice.getUuid())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .trackingCode(invoice.getTrackingCode())
                .creatorId(invoice.getCreatorId())
                .payerId(invoice.getPayerId())
                .description(invoice.getDescription())
                .params(invoice.getParams())
                .createdAt(invoice.getCreatedAt())
                .updatedAt(invoice.getUpdatedAt())
                .paymentMethod(invoice.getPaymentMethod())
                .paymentReference(invoice.getPaymentReference())
                .build();
    }
}
