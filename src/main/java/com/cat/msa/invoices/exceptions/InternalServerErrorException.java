package com.cat.msa.invoices.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message, Exception e) {
        super(message);
    }
}
