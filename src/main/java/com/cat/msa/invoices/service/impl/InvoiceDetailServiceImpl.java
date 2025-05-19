package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exceptions.BadRequestException;
import com.cat.msa.invoices.exceptions.InternalServerErrorException;
import com.cat.msa.invoices.exceptions.NotContentException;
import com.cat.msa.invoices.exceptions.NotFoundException;
import com.cat.msa.invoices.repository.InvoiceDetailRepository;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.service.InvoiceDetailService;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    private InvoiceHeaderService invoiceHeaderService;

    private final InvoiceDetailRepository invoiceDetailRepository;
    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceDetailServiceImpl(InvoiceDetailRepository invoiceDetailRepository,
                                    InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }

    @Override
    public InvoiceDetail createInvoiceDetail(InvoiceDetail invoiceDetail) {
        try {
            if (invoiceDetail.getInvoice() == null || invoiceDetail.getInvoice().getId() == null) {
                throw new BadRequestException("Debe proporcionar el ID del encabezado de factura.");
            }

            Long headerId = invoiceDetail.getInvoice().getId();
            InvoiceHeader existingHeader = invoiceHeaderRepository.findById(headerId)
                    .orElseThrow(() -> new NotFoundException("Factura con ID " + headerId + " no encontrada."));

            invoiceDetail.setInvoice(existingHeader);
            invoiceDetail.calculateSubtotal();
            return invoiceDetailRepository.save(invoiceDetail);

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();

            String errorMessage = "Error al crear el detalle de la factura. Detalles: " + e.getMessage() + "\nStackTrace:\n" + stackTrace;
            throw new InternalServerErrorException(errorMessage, e);
        }
    }

    @Override
    public InvoiceDetail findByIdDetail(Integer id) {
        return invoiceDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Factura con ID " + id + " no encontrada."));
    }


    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail, Integer id) {
        if (invoiceDetail == null) {
            throw new BadRequestException("El cuerpo de la factura no puede ser nulo.");
        }
        InvoiceDetail invoiceToUpdate = this.findByIdDetail(id);
        invoiceToUpdate.update(invoiceDetail);
        return invoiceDetailRepository.save(invoiceToUpdate);
    }

    @Override
    public List<InvoiceDetail> getAllDetails() {
        List<InvoiceDetail> invoicesDetails = invoiceDetailRepository.findAll();
        if (invoicesDetails.isEmpty()) {
            throw new NotContentException("No hay facturas registradas.");
        }
        return invoicesDetails;
    }

    @Override
    public void deleteByIdDetail(Integer id) {
        this.findByIdDetail(id); // Lanza NotFoundException si no existe
        try {
            invoiceDetailRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al eliminar la factura Detalle.", e);
        }
    }
}
