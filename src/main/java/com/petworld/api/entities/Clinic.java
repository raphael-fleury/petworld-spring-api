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
public class Clinic {

    @Id @UuidGenerator @ColumnDefault("RANDOM_UUID()")
    private UUID id;
    private String name;
    private String email;
    private String code;
    private String password;

    public Clinic(String name, String email, String code, String password) {
        this.name = name;
        this.email = email;
        this.code = code;
        this.password = password;
    }
}
