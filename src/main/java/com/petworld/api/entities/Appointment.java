package com.petworld.api.entities;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Appointment {
    
    @ManyToOne private Clinic clinic;
    @ManyToOne private Veterinary veterinary;
    @ManyToOne private Pet pet;

    @Id @UuidGenerator @ColumnDefault("RANDOM_UUID()")
    private UUID id;

    private Date date;

    public Appointment(Veterinary veterinary, Pet pet, Date date) {
        this.clinic = veterinary.getClinic();
        this.veterinary = veterinary;
        this.pet = pet;
        this.date = date;
    }
}
