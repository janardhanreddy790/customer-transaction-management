package com.nagam.poc.customer_transaction_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO class to represent JWT response.
 */
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    /**
     * Constructor to initialize JwtResponse with token, id, username, email, and roles.
     *
     * @param token    JWT token
     * @param id       User ID
     * @param username Username
     * @param email    Email address
     * @param roles    List of user roles
     */
    public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.type = "Bearer"; // Set the type explicitly
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
