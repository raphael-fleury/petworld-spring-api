package com.petworld.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petworld.api.dto.auth.LoginDTO;
import com.petworld.api.dto.clinic.ClinicInsertDTO;
import com.petworld.api.dto.clinic.ClinicResponseDTO;
import com.petworld.api.entities.Clinic;
import com.petworld.api.repositories.ClinicRepository;

import jakarta.validation.Valid;

@RestController
public class AuthController {
    
    @Autowired
    private ClinicRepository clinicRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO form) {
        var clinic = clinicRepository.findByCodeAndPassword(form.code(), form.password());
        return clinic != null ? ResponseEntity.ok("Bearer example_token") : ResponseEntity.notFound().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ClinicResponseDTO> signUp(@RequestBody @Valid ClinicInsertDTO form) {
        var clinic = new Clinic();
        BeanUtils.copyProperties(form, clinic);
        clinic.setCode(generateClinicCode());
        var response = new ClinicResponseDTO(clinicRepository.save(clinic));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private String generateClinicCode() {
        var letters = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        var numbers = "123456789";
        var code = "";

        for (int i = 0; i < 3; i++) {
            int index = (int)Math.floor(Math.random() * letters.length());
            code += letters.charAt(index);
            index = (int)Math.floor(Math.random() * numbers.length());
            code += numbers.charAt(index);
        }

        if (clinicRepository.existsByCode(code))
            code = generateClinicCode();

        return code;
    }
}
