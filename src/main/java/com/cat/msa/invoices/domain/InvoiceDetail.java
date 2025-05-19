package com.cat.msa.invoices.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.function.Consumer;
import java.util.function.Supplier;

=======
//
>>>>>>> feature/detail
@Getter
@Setter
@Entity
@Table(name = "T_INVOICE_DETAILS")
//@Table(name = "invoicedetail")

public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IND_ID", nullable = false)
    private Long id;

    @Column(name = "IND_PROD_NAME", nullable = false)
    private String productName;

    @Column(name = "IND_QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "IND_UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "IND_SUB_TOTAL", nullable = false)
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    @JsonBackReference
    private InvoiceHeader invoice;


    //calcular subtotal
    public void calculateSubtotal() {
        if (quantity != null && unitPrice != null) {
            this.subTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    // Métodos de actualización
    public void update(InvoiceDetail invoiceDetail) {
        if (invoiceDetail == null) {
            throw new RuntimeException("Factura detalle no puede ser null");
        }

        updateIfDifferent(this::getQuantity, this::setQuantity, invoiceDetail.getQuantity());
        updateIfDifferent(this::getSubTotal, this::setSubTotal, invoiceDetail.getSubTotal());
        updateIfDifferent(this::getProductName, this::setProductName, invoiceDetail.getProductName());
        updateIfDifferent(this::getUnitPrice, this::setUnitPrice, invoiceDetail.getUnitPrice());
    }

    private <T> void updateIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        T currentValue = getter.get();
        if ((currentValue == null && newValue != null) ||
                (currentValue != null && !currentValue.equals(newValue))) {
            setter.accept(newValue);
        }
    }


}