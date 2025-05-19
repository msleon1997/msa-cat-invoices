package com.cat.msa_invoices.Service;

import com.cat.msa.invoices.domain.InvoiceDetail;
import com.cat.msa.invoices.domain.InvoiceHeader;
import com.cat.msa.invoices.repository.InvoiceHeaderRepository;
import com.cat.msa.invoices.service.impl.InvoiceHeaderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)

public class InvoiceHeaderServiceTest {
    @InjectMocks
    private InvoiceHeaderServiceImpl invoiceHeaderService;
    @Mock
    private InvoiceHeaderRepository invoiceHeaderRepository;

    @Test
    void  shouldReturnInvoice_whenSendCorrectEmployeeData() throws ParseException {



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
        //when
        Mockito.when(invoiceHeaderRepository.save(any())).thenReturn(expectedInvoice);


        //then
        InvoiceHeader response = invoiceHeaderService.create(expectedInvoice);
        //assert
        assertEquals(expectedInvoice.getDetails(), response.getDetails());

    }
}
