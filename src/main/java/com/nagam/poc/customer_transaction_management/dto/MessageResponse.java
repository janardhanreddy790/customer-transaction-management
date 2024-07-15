package com.nagam.poc.customer_transaction_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class to represent generic message response.
 */
@Data
@AllArgsConstructor
public class MessageResponse {
  private String message;
}