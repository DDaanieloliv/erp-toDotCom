package com.example.erp_todotcom.service;

import com.example.erp_todotcom.rest.dto.CreateUserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService {

    void optionCreation(CreateUserDTO dto, Jwt jwt);

}
