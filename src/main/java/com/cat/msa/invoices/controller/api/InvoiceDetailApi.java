package com.cat.msa.invoices.controller.api;

import com.cat.msa.invoices.domain.InvoiceDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/invoice-details")
public interface InvoiceDetailApi {

    @PostMapping("/details")
    ResponseEntity<InvoiceDetail> createInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail);

    @GetMapping("/details")
    ResponseEntity<List<InvoiceDetail>> findAllDetail();

    @GetMapping("/details/{id}")
    ResponseEntity<InvoiceDetail> findByIdDetail(@PathVariable Long id);

    ResponseEntity<InvoiceDetail> findByIdDetail(Integer id);

    @PutMapping("/details/{id}")
    ResponseEntity<InvoiceDetail> updateDetail(@RequestBody InvoiceDetail invoiceDetail, @PathVariable Integer id);

    ResponseEntity<InvoiceDetail> updateDetail(InvoiceDetail invoiceDetail, Long id);

    @DeleteMapping("/details/{id}")
    ResponseEntity<Void> deleteByIdDetail(@PathVariable Integer id);
}
