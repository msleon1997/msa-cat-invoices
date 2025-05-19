package com.cat.msa.invoices.controller.api;

import com.cat.msa.invoices.domain.InvoiceHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/invoice-headers")
public interface InvoiceHeaderApi {
    @PostMapping
    ResponseEntity<InvoiceHeader> save(@RequestBody InvoiceHeader invoiceHeader);

    @GetMapping
    ResponseEntity<List<InvoiceHeader>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<InvoiceHeader> findById(@PathVariable Long id);

    @GetMapping("/number/{number}")
    ResponseEntity<InvoiceHeader> findByNumber(@PathVariable String number);

    @PutMapping("/{id}")
    ResponseEntity<InvoiceHeader> update(@RequestBody InvoiceHeader invoiceHeader, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}