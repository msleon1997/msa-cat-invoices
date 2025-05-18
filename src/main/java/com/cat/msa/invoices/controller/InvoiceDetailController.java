package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.controller.api.InvoiceDetailApi;
import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.service.InvoiceDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceDetailController implements InvoiceDetailApi {

    private final InvoiceDetailService invoiceDetailService;

    public InvoiceDetailController(InvoiceDetailService invoiceDetailService) {
        this.invoiceDetailService = invoiceDetailService;
    }

    @Override
    public ResponseEntity<InvoiceDetail> save(InvoiceDetail invoiceDetail) {
        InvoiceDetail savedInvoiceDetail = invoiceDetailService.createInvoiceDetail(invoiceDetail);
        return new ResponseEntity<>(savedInvoiceDetail, HttpStatus.CREATED);
    }
}
