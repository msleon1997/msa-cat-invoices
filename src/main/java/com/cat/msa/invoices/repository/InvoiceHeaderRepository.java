package com.cat.msa.invoices.repository;

import com.cat.msa.invoices.domain.InvoiceHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
<<<<<<< HEAD

public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, Long> {
    Optional<InvoiceHeader> findByNumber(String number);
=======
//
public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, Long> {
    Optional<InvoiceHeader> findByInvoiceNumber(String invoiceNumber);
>>>>>>> feature/detail
}