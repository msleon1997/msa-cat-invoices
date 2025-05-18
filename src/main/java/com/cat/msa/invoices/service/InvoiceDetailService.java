package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceDetail;


import java.util.List;

public interface InvoiceDetailService {
    InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail);

    List<InvoiceDetail> findAll();

    InvoiceDetail getInvoiceDetailById(Long id);
}
