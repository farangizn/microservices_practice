package org.example.customer.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.customer.entity.Customer}
 */
@Value
public class CustomerDTO implements Serializable {
    String email;
    String firstName;
    String lastName;
}