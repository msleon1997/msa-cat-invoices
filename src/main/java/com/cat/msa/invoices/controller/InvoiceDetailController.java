package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.controller.api.InvoiceDetailApi;
import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.service.InvoiceDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public ResponseEntity<List<InvoiceDetail>> findAll() {
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAll();
        if (invoiceDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoiceDetails);
    }

    @Override
    public ResponseEntity<InvoiceDetail> findById(Long id) {
        InvoiceDetail invoiceDetail = invoiceDetailService.getInvoiceDetailById(id);
        if (invoiceDetail != null) {
            return new ResponseEntity<>(invoiceDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
