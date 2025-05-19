package com.cat.msa.invoices.domain;

import com.cat.msa.invoices.constant.Constant;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Getter
@Setter
@Entity
@Table(name = "T_INVOICE_HEADERS")
//@Table(name = "invoiceheader")

public class InvoiceHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INH_ID", nullable = false)
    private Long id;

    @Column(name = "INH_NUMBER", nullable = false)
    private String number;

    @Column(name = "INH_CUS_NAME", nullable = false)
    private String customerName;

    @Column(name = "INH_DATE", nullable = false)
    private Date date;

    @Column(name = "INH_SUB_TOTAL", nullable = false)
    private BigDecimal subTotalAmount;

    @Column(name = "INH_VAT_AMOUNT", nullable = false)
    private BigDecimal vatAmount;

    @Column(name = "INH_TOTAL", nullable = false)
    private BigDecimal totalAmount;


    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InvoiceDetail> details = new ArrayList<>();

    public void calculateInvoiceAmmount(){
        calculateSubTotalAmount();
        calculateVatAmount();
        calculateTotalAmount();
        addInvoiceDetail();
    }

    public void calculateSubTotalAmount() {
        subTotalAmount = BigDecimal.ZERO;
        for (InvoiceDetail invoiceDetail : details) {
            invoiceDetail.calculateSubtotal();
            subTotalAmount = subTotalAmount.add(invoiceDetail.getSubTotal());
        }
    }

    public void calculateVatAmount() {
        vatAmount = subTotalAmount.multiply(Constant.VAT_RATE);
    }

    public void calculateTotalAmount() {
        totalAmount = subTotalAmount.add(vatAmount);
    }

    public void addInvoiceDetail(){
        for ( InvoiceDetail invoiceDetail : details) {
            invoiceDetail.setInvoice(this);
        }
    }

    public void update(InvoiceHeader invoiceHeader) {
        if (invoiceHeader == null) {
            throw new RuntimeException("Factura a actualizar no puede ser null");
        }
        updateIfDifferent(this::getNumber, this::setNumber, invoiceHeader.getNumber());
        updateIfDifferent(this::getDate, this::setDate, invoiceHeader.getDate());
        updateIfDifferent(this::getCustomerName, this::setCustomerName, invoiceHeader.getCustomerName());

        if (invoiceHeader.getDetails() != null) {
            this.details.clear();
            for (InvoiceDetail detail : invoiceHeader.getDetails()) {
                detail.setInvoice(this);
                detail.calculateSubtotal();
                this.details.add(detail);
            }
        }

        this.calculateSubTotalAmount();
        this.calculateVatAmount();
        this.calculateTotalAmount();
    }


    private <T> void updateIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        T currentValue = getter.get();
        if ((currentValue == null && newValue != null) ||
                (currentValue != null && !currentValue.equals(newValue))) {
            setter.accept(newValue);
        }
    }

    public void updateInvoiceDate(Date newDate) {
        if (newDate == null) {
            throw new RuntimeException("Invoice date not be null!!");
        }
        this.setDate(newDate);
    }

}