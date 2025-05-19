package com.cat.msa.invoices.service.impl;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
<<<<<<< HEAD
import com.cat.msa.invoices.exceptions.BadRequestException;
import com.cat.msa.invoices.exceptions.InternalServerErrorException;
import com.cat.msa.invoices.exceptions.NotFoundException;
=======
import com.cat.msa.invoices.exception.ResourceNotFoundException;
>>>>>>> feature/detail
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;//
import java.util.List;
<<<<<<< HEAD

=======
import java.util.Optional;//
>>>>>>> feature/detail

@Service
public class InvoiceHeaderServiceImpl implements InvoiceHeaderService {

    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceHeaderServiceImpl(InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }


    @Override
<<<<<<< HEAD
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
=======
    @Transactional
    public InvoiceHeader createInvoiceHeader(InvoiceHeader invoiceHeader) {
        invoiceHeader.calculateInvoiceAmmount();
>>>>>>> feature/detail
        return invoiceHeaderRepository.save(invoiceHeader);
    }




    @Override
<<<<<<< HEAD
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


=======
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
>>>>>>> feature/detail
}