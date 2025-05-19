package com.cat.msa.invoices.controller.api;

import com.cat.msa.invoices.domain.InvoiceHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/invoice-headers")
public interface InvoiceHeaderApi {
    @PostMapping
    ResponseEntity<InvoiceHeader> createInvoiceHeader(@RequestBody InvoiceHeader invoiceHeader);

    ResponseEntity<InvoiceHeader> save(InvoiceHeader invoiceHeader);

    @GetMapping
    ResponseEntity<List<InvoiceHeader>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<InvoiceHeader> findById(@PathVariable Long id);

    @GetMapping("/number/{number}")
<<<<<<< HEAD
    ResponseEntity<InvoiceHeader> findByNumber(@PathVariable("number") String number);

    @PutMapping("/{id}")
    ResponseEntity<InvoiceHeader> update(@RequestBody InvoiceHeader invoiceHeader, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PatchMapping("/{id}/date-invoice")
    ResponseEntity<InvoiceHeader> updateInvoiceHeaderByDate(@RequestBody InvoiceHeader date, @PathVariable Integer id);


=======
    ResponseEntity<InvoiceHeader> findByNumber(@PathVariable String number);

    @PutMapping("/{id}")
    ResponseEntity<InvoiceHeader> update(@RequestBody InvoiceHeader invoiceHeader, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id);
>>>>>>> feature/detail
}

//methods of create, put, post and delete