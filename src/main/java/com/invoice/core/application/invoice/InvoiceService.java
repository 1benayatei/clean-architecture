package com.invoice.core.application.invoice;

import com.invoice.core.domain.invoice.Invoice;
import com.invoice.core.infrastructure.spring_boot.web.common.ParamsItemDto;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    Invoice create(Invoice invoice);

    Invoice cancel(UUID uuid);

    Invoice updateToPaid(UUID uuid, Invoice.InvoicePaymentMethod paymentMethod, String payerId, List<ParamsItemDto> params);

    Invoice updateToFailed(UUID uuid, String payerId, List<ParamsItemDto> params);

    Invoice findByUuidAndCreatorId(UUID uuid, String creatorId);

    Invoice findByUuid(UUID uuid);

    Invoice update(Invoice invoice);
}
