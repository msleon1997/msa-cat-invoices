package com.cat.msa.invoices.repository;

import com.cat.msa.invoices.domain.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
//
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
}