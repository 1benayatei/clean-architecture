package com.invoice.core.infrastructure.spring_boot.config;

import com.invoice.core.CoreApplication;
import com.invoice.core.application.invoice.InvoiceService;
import com.invoice.core.application.invoice.DefaultInvoiceService;
import com.invoice.core.domain.invoice.InvoiceBusinessLogic;
import com.invoice.core.domain.invoice.InvoiceRepository;
import com.invoice.core.infrastructure.spring_boot.db.invoice.jpa.InvoiceJpaRepository;
import com.invoice.core.infrastructure.spring_boot.db.invoice.jpa.JpaInvoiceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CoreApplication.class)
public class AppConfig {
    @Bean
    public InvoiceRepository invoiceRepository(InvoiceJpaRepository invoiceJpaRepository) {
        return new JpaInvoiceRepository(invoiceJpaRepository);
    }

    @Bean
    public InvoiceBusinessLogic invoiceBusinessLogic(final InvoiceRepository invoiceRepository) {
        return new InvoiceBusinessLogic(invoiceRepository);
    }

    @Bean
    public InvoiceService invoiceService(final InvoiceRepository invoiceRepository, InvoiceBusinessLogic invoiceBusinessLogic) {
        return new DefaultInvoiceService(invoiceRepository, invoiceBusinessLogic);
    }

}
