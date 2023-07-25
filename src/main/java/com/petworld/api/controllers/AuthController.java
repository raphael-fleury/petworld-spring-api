package com.petworld.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petworld.api.dto.auth.LoginDTO;
import com.petworld.api.dto.clinic.ClinicInsertDTO;

import jakarta.validation.Valid;

@RestController
public class AuthController {
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO form) {
        return ResponseEntity.ok("Bearer example_token");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid ClinicInsertDTO form) {
        return ResponseEntity.ok("Bearer example_token");
    }
}
