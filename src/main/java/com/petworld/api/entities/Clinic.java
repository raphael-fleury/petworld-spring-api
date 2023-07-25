package com.petworld.api.entities;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

import lombok.Data;

@Entity @Data
public class Clinic {

    @Id @UuidGenerator @ColumnDefault("RANDOM_UUID()")
    private UUID id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String code;
    private String password;
}
