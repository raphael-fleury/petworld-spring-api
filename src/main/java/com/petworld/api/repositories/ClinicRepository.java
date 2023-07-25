package com.petworld.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petworld.api.entities.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, UUID> {
    public Clinic findByCodeAndPassword(String code, String password);
    public Boolean existsByCode(String code);
}
