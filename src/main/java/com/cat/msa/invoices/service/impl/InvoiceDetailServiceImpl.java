package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.exception.ResourceNotFoundException;
import com.cat.msa.invoices.repository.InvoiceDetailRepository;
import com.cat.msa.invoices.service.InvoiceDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    private final InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailServiceImpl(InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    @Override
    @Transactional
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
        return invoiceDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de factura no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public InvoiceDetail updateInvoiceDetail(Long id, InvoiceDetail updatedDetail) {
        InvoiceDetail existingDetail = getInvoiceDetailById(id);

        existingDetail.setProductName(updatedDetail.getProductName());
        existingDetail.setQuantity(updatedDetail.getQuantity());
        existingDetail.setUnitPrice(updatedDetail.getUnitPrice());
        existingDetail.calculateSubTotal();

        return invoiceDetailRepository.save(existingDetail);
    }

    @Override
    @Transactional
    public void deleteByIdDetail(Long id) {
        InvoiceDetail detail = getInvoiceDetailById(id);
        invoiceDetailRepository.delete(detail);
    }
}