package com.cogent.ecom.rest.api.payload;

import com.cogent.ecom.rest.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private boolean error;
    private String message;
    private User user;
}
