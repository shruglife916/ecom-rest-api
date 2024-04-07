package com.cogent.ecom.rest.api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EcomApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public EcomApiException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public EcomApiException(String customMessage, HttpStatus status, String message) {
        super(customMessage);
        this.message = message;
        this.status = status;
    }
}
