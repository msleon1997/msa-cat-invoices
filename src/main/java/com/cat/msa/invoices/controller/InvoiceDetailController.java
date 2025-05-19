package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.controller.api.InvoiceDetailApi;
import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.service.InvoiceDetailService;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceDetailController implements InvoiceDetailApi {
    @Autowired
    private final InvoiceDetailService invoiceDetailService;
    @Autowired
    private InvoiceHeaderService invoiceHeaderService;
    public InvoiceDetailController(InvoiceDetailService invoiceService) {
        this.invoiceDetailService = invoiceService;
    }

    @Override
    public ResponseEntity<InvoiceDetail> createInvoiceDetail(InvoiceDetail invoiceDetail) {
        invoiceDetail.calculateSubtotal();
        InvoiceDetail savedInvoiceDetail = invoiceDetailService.createInvoiceDetail(invoiceDetail);
        InvoiceHeader invoiceHeader = invoiceHeaderService.findById(Math.toIntExact(savedInvoiceDetail.getInvoice().getId()));
        invoiceHeader.calculateSubTotalAmount();
        invoiceHeader.calculateVatAmount();
        invoiceHeader.calculateTotalAmount();
        invoiceHeaderService.update(invoiceHeader);
        return new ResponseEntity<>(savedInvoiceDetail, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<InvoiceDetail>> findAllDetail() {
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.getAllDetails();
        return new ResponseEntity<>(invoiceDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceDetail> findByIdDetail(Long id) {
        InvoiceDetail invoiceDetail = invoiceDetailService.findByIdDetail(Math.toIntExact(id));
        return new ResponseEntity<>(invoiceDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceDetail> updateDetail(InvoiceDetail invoiceDetail, Integer id) {
        InvoiceDetail updateInvoiceDetail = invoiceDetailService.update(invoiceDetail, id);
        InvoiceHeader invoiceHeader = updateInvoiceDetail.getInvoice();
        invoiceHeader.calculateSubTotalAmount();
        invoiceHeader.calculateVatAmount();
        invoiceHeader.calculateTotalAmount();
        invoiceHeader.update(invoiceHeader);
        invoiceDetail.update(invoiceDetail);
        return new ResponseEntity<>(updateInvoiceDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteByIdDetail(Integer id) {
        invoiceDetailService.deleteByIdDetail(id);
        return ResponseEntity.noContent().build();
    }
}
