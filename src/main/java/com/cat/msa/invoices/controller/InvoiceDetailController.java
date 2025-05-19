package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.controller.api.InvoiceDetailApi;
import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.exception.ResourceNotFoundException;
import com.cat.msa.invoices.service.InvoiceDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
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
        return invoiceDetails.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(invoiceDetails);
    }

    @Override
    public ResponseEntity<InvoiceDetail> findById(Long id) {
        try {
            InvoiceDetail detail = invoiceDetailService.getInvoiceDetailById(id);
            return ResponseEntity.ok(detail);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<InvoiceDetail> updateDetail(Long id, InvoiceDetail invoiceDetail) {
        try {
            InvoiceDetail updatedDetail = invoiceDetailService.updateInvoiceDetail(id, invoiceDetail);
            return ResponseEntity.ok(updatedDetail);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteByIdDetail(Long id) {
        try {
            invoiceDetailService.deleteByIdDetail(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}