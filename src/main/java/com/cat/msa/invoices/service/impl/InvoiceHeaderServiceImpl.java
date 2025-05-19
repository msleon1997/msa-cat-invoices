package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.exceptions.BadRequestException;
import com.cat.msa.invoices.exceptions.InternalServerErrorException;
import com.cat.msa.invoices.exceptions.NotFoundException;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InvoiceHeaderServiceImpl implements InvoiceHeaderService {

    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceHeaderServiceImpl(InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }


    @Override
    public InvoiceHeader create(InvoiceHeader invoiceHeader) {
        try {
            if (invoiceHeader == null) {
                throw new BadRequestException("la cabecera de la factura no puede ser nulo.");
            }
            return invoiceHeaderRepository.save(invoiceHeader);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al crear la factura.", e);
        }
    }

    @Override
    public InvoiceHeader findById(Integer id) {
        return invoiceHeaderRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Factura con ID " + id + " no encontrada."));
    }

    @Override
    public InvoiceHeader findByNumber(String number){
        return invoiceHeaderRepository.findByNumber(number)
                .orElseThrow(() -> new NotFoundException("Factura con el numero " + number + " no encontrada."));

    }

    @Override
    public InvoiceHeader update(InvoiceHeader invoiceHeader, Integer id ) {
        if (invoiceHeader == null) {
            throw new BadRequestException("El cuerpo de la factura no puede ser nulo.");
        }
        InvoiceHeader invoiceToUpdate = this.findById(Math.toIntExact(id));
        invoiceToUpdate.update(invoiceHeader);
        return invoiceHeaderRepository.save(invoiceToUpdate);
    }

    @Override
    public InvoiceHeader update(InvoiceHeader invoiceHeader) {
        if (invoiceHeader == null) {
            throw new BadRequestException("El cuerpo de la factura no puede ser nulo.");
        }
        return invoiceHeaderRepository.save(invoiceHeader);
    }




    @Override
    public List<InvoiceHeader> getAll() {
        List<InvoiceHeader> invoices = invoiceHeaderRepository.findAll();
        return invoices;
    }


    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        try {
            invoiceHeaderRepository.deleteById(Long.valueOf(id));
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al eliminar la factura.", e);
        }
    }

    @Override
    public InvoiceHeader updateInvoiceByDate(InvoiceHeader invoiceHeader, Integer id) {
        if (invoiceHeader == null || invoiceHeader.getDate() == null) {
            throw new BadRequestException("La fecha de la factura es requerida.");
        }
        InvoiceHeader invoiceToUpdate = this.findById(id);
        invoiceToUpdate.updateInvoiceDate(invoiceHeader.getDate());
        return invoiceHeaderRepository.save(invoiceToUpdate);
    }


}