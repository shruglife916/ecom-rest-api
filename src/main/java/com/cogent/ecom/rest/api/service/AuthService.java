package com.cogent.ecom.rest.api.service;

import com.cogent.ecom.rest.api.payload.LoginDto;
import com.cogent.ecom.rest.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
