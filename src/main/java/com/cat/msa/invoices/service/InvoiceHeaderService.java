package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.ResourceNotFoundException;

import java.time.LocalDate;//
import java.util.List;

public interface InvoiceHeaderService {
   InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader);

   InvoiceHeader getInvoiceHeaderById(Long id) throws ResourceNotFoundException;

   InvoiceHeader getInvoiceHeaderByNumber(String number) throws ResourceNotFoundException;

   List<InvoiceHeader> getAllInvoiceHeaders();

   InvoiceHeader updateInvoiceHeader(Long id, InvoiceHeader headerDetails) throws ResourceNotFoundException;

   void deleteInvoiceHeader(Long id) throws ResourceNotFoundException;
}
