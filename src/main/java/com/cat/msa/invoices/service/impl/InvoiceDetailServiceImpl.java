package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.repository.InvoiceDetailRepository;
import com.cat.msa.invoices.service.InvoiceDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    private final InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailServiceImpl(InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    @Override
    public InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail) {
        invoiceDetail.calculateSubTotal();
        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public List<InvoiceDetail> findAll() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public InvoiceDetail getInvoiceDetailById(Long id) {
        Optional<InvoiceDetail> optionalInvoiceDetail = invoiceDetailRepository.findById(id);
        return optionalInvoiceDetail.orElse(null);
    }
}
