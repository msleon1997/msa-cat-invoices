package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;

import java.util.List;

public interface InvoiceDetailService {
    InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail);

    InvoiceDetail findByIdDetail(Integer id);

    InvoiceDetail update(InvoiceDetail invoiceDetail, Integer id);

    List<InvoiceDetail> getAllDetails();

    void  deleteByIdDetail(Integer id);
}
