package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exception.ResourceNotFoundException;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;//
import java.util.List;
import java.util.Optional;//

@Service
public class InvoiceHeaderServiceImpl implements InvoiceHeaderService {

    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceHeaderServiceImpl(InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }

    @Override
    @Transactional
    public InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader) {
        invoiceHeader.calculateInvoiceAmmount();
        return invoiceHeaderRepository.save(invoiceHeader);
    }

    @Override
    public InvoiceHeader getInvoiceHeaderById(Long id) throws ResourceNotFoundException {
        return invoiceHeaderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceHeader no encontrado con ID: " + id));
    }

    @Override
    public InvoiceHeader getInvoiceHeaderByNumber(String number) throws ResourceNotFoundException {
        return invoiceHeaderRepository.findByInvoiceNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceHeader no encontrado con número: " + number));
    }

    @Override
    public List<InvoiceHeader> getAllInvoiceHeaders() {
        return invoiceHeaderRepository.findAll();
    }

    @Override
    @Transactional
    public InvoiceHeader updateInvoiceHeader(Long id, InvoiceHeader headerDetails) throws ResourceNotFoundException {
        InvoiceHeader existingHeader = getInvoiceHeaderById(id);

        existingHeader.setInvoiceNumber(headerDetails.getInvoiceNumber());
        existingHeader.setCustomerName(headerDetails.getCustomerName());
        existingHeader.setDate(headerDetails.getDate());

        if (headerDetails.getInvoiceDetails() != null) {
            existingHeader.getInvoiceDetails().clear();

            for (InvoiceDetail detail : headerDetails.getInvoiceDetails()) {
                detail.setInvoiceHeader(existingHeader);
                existingHeader.getInvoiceDetails().add(detail);
            }
        }

        existingHeader.calculateInvoiceAmmount();
        return invoiceHeaderRepository.save(existingHeader);
    }

    @Override
    @Transactional
    public void deleteInvoiceHeader(Long id) throws ResourceNotFoundException {
        InvoiceHeader header = getInvoiceHeaderById(id);
        invoiceHeaderRepository.delete(header);
    }
}