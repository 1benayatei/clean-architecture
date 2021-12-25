package com.invoice.core.infrastructure.spring_boot.web.invoice;

import com.invoice.core.application.invoice.InvoiceService;
import com.invoice.core.infrastructure.spring_boot.web.invoice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@Valid @RequestBody InvoiceCreationDto invoiceCreationDto) {
        var invoice = invoiceCreationDto.toInvoice();
        var createdInvoice = this.invoiceService.create(invoice);
        var result = InvoiceDto.fromInvoice(createdInvoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<InvoiceDto> getByUuid(@PathVariable UUID uuid) {
        var invoice = this.invoiceService.findByUuid(uuid);
        var result = InvoiceDto.fromInvoice(invoice);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{uuid}/creator-id/{creatorId}")
    public ResponseEntity<InvoiceDto> getByUuidAndCreatorId(@PathVariable UUID uuid, @PathVariable String creatorId) {
        var invoice = this.invoiceService.findByUuidAndCreatorId(uuid, creatorId);
        var result = InvoiceDto.fromInvoice(invoice);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<InvoiceDto> cancel(@PathVariable UUID uuid, @Valid @RequestBody InvoiceUpdateDto invoiceUpdateDto) {
        var invoice = invoiceUpdateDto.toInvoice();
        invoice.setUuid(uuid);
        invoice = this.invoiceService.update(invoice);
        var result = InvoiceDto.fromInvoice(invoice);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/{uuid}/paid")
    public ResponseEntity<InvoiceDto> updateToPaid(@PathVariable UUID uuid, @Valid @RequestBody InvoiceToPaidDto invoiceToPaidDto) {
        var invoice = this.invoiceService.updateToPaid(uuid,invoiceToPaidDto.getPaymentMethod(), invoiceToPaidDto.getPayerId(), invoiceToPaidDto.getParams());
        var result = InvoiceDto.fromInvoice(invoice);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/{uuid}/failed")
    public ResponseEntity<InvoiceDto> updateToFailed(@PathVariable UUID uuid, @Valid @RequestBody InvoiceToFailedDto invoiceToFailedDto) {
        var invoice = this.invoiceService.updateToFailed(uuid, invoiceToFailedDto.getPayerId(), invoiceToFailedDto.getParams());
        var result = InvoiceDto.fromInvoice(invoice);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/{uuid}/cancel")
    public ResponseEntity<InvoiceDto> cancel(@PathVariable UUID uuid) {
        var invoice = this.invoiceService.cancel(uuid);
        var result = InvoiceDto.fromInvoice(invoice);
        return ResponseEntity.ok().body(result);
    }
}
