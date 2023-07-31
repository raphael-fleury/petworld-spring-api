package com.petworld.api.controllers;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petworld.api.dto.customer.CustomerInsertDTO;
import com.petworld.api.entities.Customer;
import com.petworld.api.repositories.ClinicRepository;
import com.petworld.api.repositories.CustomerRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final UUID CLINIC_ID = UUID.fromString("2c2f831c-9c83-4596-9082-06f250c0b7a8");

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ClinicRepository clinicRepository;

    @GetMapping
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findByClinicId(CLINIC_ID, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable UUID id) {
        var customer = customerRepository.findByIdAndClinicId(id, CLINIC_ID);

        if (customer == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customer);
    }

    @PostMapping @Transactional
    public ResponseEntity<Customer> post(@RequestBody @Valid CustomerInsertDTO dto) {
        var customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        
        var clinic = clinicRepository.findById(CLINIC_ID).get();
        customer.setClinic(clinic);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(customerRepository.save(customer));
    }

    @PutMapping("/{id}") @Transactional
    public ResponseEntity<Customer> put(@PathVariable UUID id, @RequestBody CustomerInsertDTO dto) {
        var customer = customerRepository.findByIdAndClinicId(id, CLINIC_ID);

        if (customer == null)
            return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(dto, customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<Customer> delete(@PathVariable UUID id) {
        var customer = customerRepository.findByIdAndClinicId(id, CLINIC_ID);

        if (customer == null)
            return ResponseEntity.notFound().build();

        customerRepository.delete(customer);
        return ResponseEntity.ok(customer);
    }
}
