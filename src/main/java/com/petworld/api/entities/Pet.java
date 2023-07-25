package com.petworld.api.entities;

import jakarta.persistence.*;

import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import com.petworld.api.entities.enums.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Pet {

    @ManyToOne
    private Customer owner;

    @Id @UuidGenerator @ColumnDefault("RANDOM_UUID()")
    private UUID id;
    private String name;
    private String species;
    private String breed;
    private Boolean neutered;

    @Enumerated(EnumType.STRING)
    private Sex sex;
}
