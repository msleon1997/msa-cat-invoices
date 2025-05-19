package com.cat.msa.invoices.repository;

import com.cat.msa.invoices.domain.InvoiceHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//
public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, Long> {
    Optional<InvoiceHeader> findByInvoiceNumber(String invoiceNumber);
}