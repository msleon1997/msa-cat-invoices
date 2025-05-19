package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.ResourceNotFoundException;

import java.time.LocalDate;//
import java.util.List;

public interface InvoiceHeaderService {
   InvoiceHeader create(InvoiceHeader invoiceHeader);

<<<<<<< HEAD
   InvoiceHeader findById(Integer id);
   InvoiceHeader findByNumber(String number);


   InvoiceHeader update(InvoiceHeader invoiceHeader, Integer id);
   InvoiceHeader update(InvoiceHeader invoiceHeader);

   List<InvoiceHeader> getAll();

   void deleteById(Integer id);
   InvoiceHeader updateInvoiceByDate(InvoiceHeader invoiceHeader, Integer id);
=======
   InvoiceHeader getInvoiceHeaderById(Long id) throws ResourceNotFoundException;

   InvoiceHeader getInvoiceHeaderByNumber(String number) throws ResourceNotFoundException;

   List<InvoiceHeader> getAllInvoiceHeaders();

   InvoiceHeader updateInvoiceHeader(Long id, InvoiceHeader headerDetails) throws ResourceNotFoundException;

   void deleteInvoiceHeader(Long id) throws ResourceNotFoundException;
>>>>>>> feature/detail
}
