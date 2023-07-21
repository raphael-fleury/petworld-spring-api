package com.petworld.api.entities;

import jakarta.persistence.*;

import com.petworld.api.entities.enums.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Pet {

    @ManyToOne
    private Customer owner;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Boolean neutered;

    @Enumerated(EnumType.STRING)
    private Sex sex;
}
