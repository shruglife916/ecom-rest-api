package com.cogent.ecom.rest.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String details;
}
