package com.invoice.core.infrastructure.spring_boot.web.invoice.dto;

import com.invoice.core.domain.invoice.Invoice;
import com.invoice.core.infrastructure.spring_boot.web.common.ParamsItemDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class InvoiceToPaidDto {
    @Nullable
    private String payerId;

    @Nullable
    private List<ParamsItemDto> params;

    @NotNull
    private Invoice.InvoicePaymentMethod paymentMethod;
}
