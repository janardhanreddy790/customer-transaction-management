package com.nagam.poc.customer_transaction_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO class to represent login request.
 */
@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
