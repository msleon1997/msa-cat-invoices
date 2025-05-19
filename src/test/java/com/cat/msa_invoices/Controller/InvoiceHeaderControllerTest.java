package com.cat.msa_invoices.Controller;

import com.cat.msa.invoices.controller.InvoiceHeaderController;
import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.service.InvoiceHeaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class InvoiceHeaderControllerTest {
    @Mock
    private InvoiceHeaderService invoiceHeaderService;

    @InjectMocks
    private InvoiceHeaderController invoiceHeaderController;

    @Test
    void shouldReturnInvoiceHeader_whenSendCorrectInvoiceHeader() throws ParseException {
        // given
        InvoiceHeader expectedInvoice = new InvoiceHeader();
        expectedInvoice.setNumber("INV-1001");
        expectedInvoice.setCustomerName("Mateo Bravo");
        expectedInvoice.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2025-05-19"));

        InvoiceDetail detail1 = new InvoiceDetail();
        detail1.setProductName("Producto A");
        detail1.setQuantity(20);
        detail1.setUnitPrice(BigDecimal.valueOf(15.50));

        InvoiceDetail detail2 = new InvoiceDetail();
        detail2.setProductName("Producto B");
        detail2.setQuantity(10);
        detail2.setUnitPrice(BigDecimal.valueOf(30.00));

        List<InvoiceDetail> detailList = new ArrayList<>();
        detailList.add(detail1);
        detailList.add(detail2);

        expectedInvoice.setDetails(detailList);

        // when
        Mockito.when(invoiceHeaderService.create(any(InvoiceHeader.class))).thenReturn(expectedInvoice);

        // then
        ResponseEntity<InvoiceHeader> response = invoiceHeaderController.createInvoiceHeader(expectedInvoice);

        // assert
        InvoiceHeader result = response.getBody();
        assertEquals("INV-1001", Objects.requireNonNull(result).getNumber());
        assertEquals("Mateo Bravo", result.getCustomerName());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2025-05-19"), result.getDate());

        assertEquals(2, result.getDetails().size());

        assertEquals("Producto A", result.getDetails().get(0).getProductName());
        assertEquals(20, result.getDetails().get(0).getQuantity());
        assertEquals(BigDecimal.valueOf(15.50), result.getDetails().get(0).getUnitPrice());

        assertEquals("Producto B", result.getDetails().get(1).getProductName());
        assertEquals(10, result.getDetails().get(1).getQuantity());
        assertEquals(BigDecimal.valueOf(30.00), result.getDetails().get(1).getUnitPrice());
    }

}
