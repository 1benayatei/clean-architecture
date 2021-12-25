package com.invoice.core.infrastructure.spring_boot.web.invoice.dto;

import com.invoice.core.domain.invoice.Invoice;
import com.invoice.core.infrastructure.spring_boot.web.common.ParamsItemDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class InvoiceCreationDto {
    @NotNull
    @Min(20000)
    private int amount;
    @NotNull
    private String creatorId;
    @NotNull
    @Length(min = 10, max = 100)
    private String description;
    @NotNull
    private Invoice.InvoicePaymentMethod paymentMethod = Invoice.InvoicePaymentMethod.GATEWAY;
    private List<ParamsItemDto> params;

    public Invoice toInvoice() {
        var invoice = new Invoice();
        invoice.setAmount(amount);
        invoice.setDescription(description);
        invoice.setCreatorId(creatorId);
        invoice.setPaymentMethod(paymentMethod);
        for (var param : params) {
            if (param.getValue() != null) {
                invoice.getParams().put(param.getKey(), param.getValue());
            }
        }
        return invoice;
    }
}
