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
<<<<<<< HEAD
=======

    @GetMapping
    ResponseEntity<List<InvoiceDetail>> findAll();
    @GetMapping("/{id}")
    ResponseEntity<InvoiceDetail> findById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<InvoiceDetail> updateDetail(@PathVariable Long id, @RequestBody InvoiceDetail invoiceDetail);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteByIdDetail(@PathVariable Long id);
>>>>>>> feature/detail
}

//methods of create, put, post and delete