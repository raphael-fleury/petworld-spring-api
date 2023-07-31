package com.petworld.api.controllers;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import com.petworld.api.dto.customer.CustomerResponseDTO;
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
    public Page<CustomerResponseDTO> getAll(Pageable pageable) {
        var page = customerRepository.findByClinicId(CLINIC_ID, pageable);
        return page.map(CustomerResponseDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable UUID id) {
        var optional = customerRepository.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        var body = new CustomerResponseDTO(optional.get());
        return ResponseEntity.ok(body);
    }

    @PostMapping @Transactional
    public ResponseEntity<CustomerResponseDTO> post(@RequestBody @Valid CustomerInsertDTO dto) {
        var customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        
        var clinic = clinicRepository.findById(CLINIC_ID).get();
        customer.setClinic(clinic);

        var entity = customerRepository.save(customer);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new CustomerResponseDTO(entity));
    }

    @PutMapping("/{id}") @Transactional
    public ResponseEntity<CustomerResponseDTO> put(@PathVariable UUID id, @RequestBody CustomerInsertDTO dto) {
        var optional = customerRepository.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        var customer = optional.get();
        BeanUtils.copyProperties(dto, customer);
        return ResponseEntity.ok(new CustomerResponseDTO(customer));
    }

    @DeleteMapping("/{id}") @Transactional
    public ResponseEntity<CustomerResponseDTO> delete(@PathVariable UUID id) {
        var optional = customerRepository.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        var customer = optional.get();
        customerRepository.delete(customer);
        return ResponseEntity.ok(new CustomerResponseDTO(customer));
    }
}
