package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceHeader;

import java.util.List;

public interface InvoiceHeaderService {
   InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader);

   InvoiceHeader getInvoiceHeaderById(Long id);

   List<InvoiceHeader> getAllInvoiceHeaders();
}
