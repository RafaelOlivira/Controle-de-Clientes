package com.rafakliv.sistema.clientes.controllers;

import com.rafakliv.sistema.clientes.CustomError.ErrorMessage;
import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/customers")
public class CustomersControllers {

    @Autowired
    private CustomersServices customersServices;

    @GetMapping
    public List<CustomersModels> displayCustomers() {
        return customersServices.displayCustomers();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomersModels customers){
        try {
            var customer = customersServices.registerCustomers(customers);
            return ResponseEntity.ok(customer);
        }catch (IllegalArgumentException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMessage);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid){
        customersServices.removeCustomers(uuid);
    }
}
