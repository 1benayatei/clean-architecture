package com.invoice.core.infrastructure.spring_boot.web.invoice.dto;

import com.invoice.core.infrastructure.spring_boot.web.common.ParamsItemDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Setter
@Getter
public class InvoiceToFailedDto {
    @Nullable
    private String payerId;
    @Nullable
    private List<ParamsItemDto> params;
}
