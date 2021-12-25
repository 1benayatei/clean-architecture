package com.invoice.core.infrastructure.spring_boot.db.invoice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Integer> {
    Optional<InvoiceEntity> findByTrackingCode(String trackingCode);

    Optional<InvoiceEntity> findByUuidAndCreatorId(UUID uuid, String creatorId);

    Optional<InvoiceEntity> findByUuid(UUID uuid);
}