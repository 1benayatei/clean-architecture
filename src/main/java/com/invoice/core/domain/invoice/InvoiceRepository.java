package com.invoice.core.domain.invoice;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository {
    Invoice create(Invoice invoice);

    Invoice save(Invoice invoice);

    Optional<Invoice> findByTrackingCode(String trackingCode);

    Optional<Invoice> findByUuid(UUID uuid);

    Optional<Invoice> findByUuidAndCreatorId(UUID uuid, String creatorId);
}
