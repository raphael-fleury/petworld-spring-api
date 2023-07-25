package com.petworld.api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
    @NotBlank
    String code,

    @Size(min = 8, max = 24)
    String password
) { }
