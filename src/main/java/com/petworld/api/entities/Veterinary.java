package com.petworld.api.entities;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Veterinary {
    
    @ManyToOne
    private Clinic clinic;

    @Id @UuidGenerator @ColumnDefault("RANDOM_UUID()")
    private UUID id;
    private String name;
    private String email;
    private String address;
    private String phone;

    public Veterinary(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
