package com.petworld.api.dto.clinic;

import java.util.UUID;

import com.petworld.api.entities.Clinic;

public record ClinicResponseDTO(UUID id, String code, String name, String email) {
    public ClinicResponseDTO(Clinic clinic) {
        this(clinic.getId(), clinic.getCode(), clinic.getName(), clinic.getEmail());
    }
}
