package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceHeader;

import java.util.List;

public interface InvoiceHeaderService {
   InvoiceHeader create(InvoiceHeader invoiceHeader);

   InvoiceHeader findById(Integer id);
   InvoiceHeader findByNumber(String number);


   InvoiceHeader update(InvoiceHeader invoiceHeader, Integer id);
   InvoiceHeader update(InvoiceHeader invoiceHeader);

   List<InvoiceHeader> getAll();

   void deleteById(Integer id);
   InvoiceHeader updateInvoiceByDate(InvoiceHeader invoiceHeader, Integer id);
}
