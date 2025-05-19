package com.cat.msa.invoices.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiExceptionHandler {
    @ExceptionHandler(NotContentException.class)
    ResponseEntity<Void> handleNotContentException(NotContentException ex) {
        return ResponseEntity.noContent().build();
    }
}
