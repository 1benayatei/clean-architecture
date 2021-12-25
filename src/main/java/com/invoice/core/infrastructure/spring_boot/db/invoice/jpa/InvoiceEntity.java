package com.invoice.core.infrastructure.spring_boot.db.invoice.jpa;

import com.fasterxml.jackson.databind.JsonNode;
import com.invoice.core.domain.invoice.Invoice.InvoicePaymentMethod;
import com.invoice.core.domain.invoice.Invoice.InvoiceStatus;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "invoice")
@Setter
@Getter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private InvoiceStatus status;

    private String trackingCode;

    private String description;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private InvoicePaymentMethod paymentMethod;

    @Column(name = "payment_reference_id")
    private String paymentReferenceId;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "payer_id")
    private String payerId;

    private UUID uuid = UUID.randomUUID();

    @Column(name = "params", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private Map<String, JsonNode> params = new HashMap<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
