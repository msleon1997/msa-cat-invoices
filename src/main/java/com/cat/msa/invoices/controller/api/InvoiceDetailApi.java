package com.cat.msa.invoices.controller.api;

import com.cat.msa.invoices.domain.InvoiceDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/invoice-details")
public interface InvoiceDetailApi {

    @PostMapping
    ResponseEntity<InvoiceDetail> save(@RequestBody InvoiceDetail invoiceDetail);
}
