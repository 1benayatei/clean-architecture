package com.invoice.core.domain.invoice;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
public class Invoice {
    private int id;
    private int amount;
    private InvoiceStatus status;
    private String trackingCode;
    private String description;
    private InvoicePaymentMethod paymentMethod;
    private String paymentReference;
    private String creatorId;
    private String payerId;
    private UUID uuid;
    private Map<String, JsonNode> params = new HashMap<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum InvoiceStatus {
        PENDING,
        PAID,
        CANCELED,
        FAILED
    }

    public enum InvoicePaymentMethod {
        GATEWAY,
        WALLET,
        OPTIONAL
    }
}
