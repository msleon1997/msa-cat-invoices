
package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.controller.api.InvoiceHeaderApi;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.ResourceNotFoundException;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
@RestController
public class InvoiceHeaderController implements InvoiceHeaderApi {

    private final InvoiceHeaderService invoiceHeaderService;

    public InvoiceHeaderController(InvoiceHeaderService invoiceHeaderService) {
        this.invoiceHeaderService = invoiceHeaderService;
    }

    @Override
    public ResponseEntity<InvoiceHeader> save(InvoiceHeader invoiceHeader) {
        InvoiceHeader savedInvoiceHeader = invoiceHeaderService.createInvoiceHeader(invoiceHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoiceHeader);
    }

    @Override
    public ResponseEntity<List<InvoiceHeader>> findAll() {
        List<InvoiceHeader> invoiceHeaders = invoiceHeaderService.getAllInvoiceHeaders();
        return invoiceHeaders.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(invoiceHeaders);
    }

    @Override
    public ResponseEntity<InvoiceHeader> findById(@PathVariable Long id) {
        try {
            InvoiceHeader invoiceHeader = invoiceHeaderService.getInvoiceHeaderById(id);
            return ResponseEntity.ok(invoiceHeader);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<InvoiceHeader> findByNumber(@PathVariable String number) {
        try {
            InvoiceHeader invoiceHeader = invoiceHeaderService.getInvoiceHeaderByNumber(number);
            return ResponseEntity.ok(invoiceHeader);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<InvoiceHeader> update(@RequestBody InvoiceHeader invoiceHeader, @PathVariable Long id) {
        try {
            InvoiceHeader updatedHeader = invoiceHeaderService.updateInvoiceHeader(id, invoiceHeader);
            return ResponseEntity.ok(updatedHeader);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            invoiceHeaderService.deleteInvoiceHeader(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
