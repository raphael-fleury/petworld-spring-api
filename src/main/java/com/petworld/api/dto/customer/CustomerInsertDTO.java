package com.petworld.api.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerInsertDTO(
    @NotBlank String name,
    @Email String email,
    String address,
    String phone
) { }
