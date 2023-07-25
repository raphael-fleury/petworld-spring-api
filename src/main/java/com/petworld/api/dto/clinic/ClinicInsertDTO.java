package com.petworld.api.dto.clinic;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClinicInsertDTO(
    @NotBlank
    String name,

    @Email
    String email,

    @Size(min = 8, max = 24)
    String password
) { }
