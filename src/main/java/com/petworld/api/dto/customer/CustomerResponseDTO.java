package com.petworld.api.dto.customer;

import java.util.UUID;

import com.petworld.api.entities.Customer;

public record CustomerResponseDTO(
    UUID id,
    String name,
    String email,
    String address,
    String phone
) {
    public CustomerResponseDTO(Customer entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getAddress(), entity.getPhone());
    }
}
