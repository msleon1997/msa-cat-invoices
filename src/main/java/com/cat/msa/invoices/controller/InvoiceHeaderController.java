package com.cat.msa.invoices.controller;

import com.cat.msa.invoices.controller.api.InvoiceHeaderApi;
import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceHeaderController implements InvoiceHeaderApi {


    private final InvoiceHeaderService invoiceHeaderService;
    public InvoiceHeaderController(InvoiceHeaderService invoiceHeaderService) {
        this.invoiceHeaderService = invoiceHeaderService;
    }


    @Override
    public ResponseEntity<InvoiceHeader> createInvoiceHeader(InvoiceHeader invoiceHeader) {
        if (invoiceHeader.getDetails() != null) {
            for (InvoiceDetail detail : invoiceHeader.getDetails()) {
                detail.setInvoice(invoiceHeader);
                detail.calculateSubtotal();
            }
        }
        //llamar a los metodos
        invoiceHeader.calculateSubTotalAmount();
        invoiceHeader.calculateVatAmount();
        invoiceHeader.calculateTotalAmount();
        InvoiceHeader savedInvoiceHeader = invoiceHeaderService.create(invoiceHeader);
        return new ResponseEntity<>(savedInvoiceHeader, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<InvoiceHeader> save(InvoiceHeader invoiceHeader) {
        return null;
    }

    @Override
    public ResponseEntity<List<InvoiceHeader>> findAll() {
        List<InvoiceHeader> invoices = invoiceHeaderService.getAll();
        if (invoices.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<InvoiceHeader> findById(Long id) {
        InvoiceHeader invoiceHeader = invoiceHeaderService.findById(Math.toIntExact(id));
        return new ResponseEntity<>(invoiceHeader, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceHeader> findByNumber(@PathVariable String number) {
        InvoiceHeader invoiceHeader = invoiceHeaderService.findByNumber(number);
        return new ResponseEntity<>(invoiceHeader, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceHeader> update(InvoiceHeader invoiceHeader, Integer id) {
        // Si vienen detalles, asegúrate de vincularlos y recalcular cada uno
        if (invoiceHeader.getDetails() != null) {
            for (InvoiceDetail detail : invoiceHeader.getDetails()) {
                detail.setInvoice(invoiceHeader);
                detail.calculateSubtotal();
            }
        }
        invoiceHeader.calculateSubTotalAmount();
        invoiceHeader.calculateVatAmount();
        invoiceHeader.calculateTotalAmount();
        InvoiceHeader updatedInvoiceHeader = invoiceHeaderService.update(invoiceHeader, id);
        return new ResponseEntity<>(updatedInvoiceHeader, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        invoiceHeaderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<InvoiceHeader> updateInvoiceHeaderByDate(InvoiceHeader invoiceHeader, Integer id) {
        InvoiceHeader updatedInvoiceHeader = invoiceHeaderService.updateInvoiceByDate(invoiceHeader, id);
        return new ResponseEntity<>(updatedInvoiceHeader, HttpStatus.OK);
    }
}
