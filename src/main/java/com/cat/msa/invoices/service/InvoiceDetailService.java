package com.cat.msa.invoices.service;

import com.cat.msa.invoices.domain.InvoiceDetail;
<<<<<<< HEAD

=======
import java.util.List;
//
>>>>>>> feature/detail
public interface InvoiceDetailService {

    InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail);
<<<<<<< HEAD
}
=======

    List<InvoiceDetail> findAll();

    InvoiceDetail getInvoiceDetailById(Long id);

    InvoiceDetail updateInvoiceDetail(Long id, InvoiceDetail updatedDetail);

    void deleteByIdDetail(Long id);
}
>>>>>>> feature/detail
