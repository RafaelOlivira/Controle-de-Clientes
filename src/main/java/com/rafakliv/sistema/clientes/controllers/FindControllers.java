package com.rafakliv.sistema.clientes.controllers;

import com.rafakliv.sistema.clientes.CustomError.ErrorMessage;
import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/findby")
public class FindControllers {

    @Autowired
    private CustomersServices customersServices;

    @GetMapping
    public List<CustomersModels> displayCustomers() {
        return customersServices.displayCustomers();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        try {
            var customer = customersServices.findByEmail(email);
            return ResponseEntity.ok(customer);
        }catch (NullPointerException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"NOT_FOUND");
            return ResponseEntity.status(404).body(errorMessage);
        }
    }

    @GetMapping("/id/{uuid}")
    public ResponseEntity<?> findByUuid(@PathVariable UUID uuid){
        try {
            UUID.fromString(uuid.toString()); // Valid UUID
            var customer = customersServices.findByUuid(uuid);
            return ResponseEntity.ok(customer);
        }catch (NullPointerException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"NOT_FOUND");
            return ResponseEntity.status(404).body(errorMessage);
        } catch (MethodArgumentTypeMismatchException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"Ilegal Argument");
            return ResponseEntity.status(400).body(errorMessage);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        try {
            var customer = customersServices.findByName(name);
            return ResponseEntity.ok(customer);
        }catch (NullPointerException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"NOT_FOUND");
            return ResponseEntity.status(404).body(errorMessage);
        }
    }

    @GetMapping("/country/{country}")
    public List<CustomersModels> findByCountry(@PathVariable String country){
        return customersServices.findByCountry(country);
    }

    @GetMapping("/city/{city}")
    public List<CustomersModels> findByCity(@PathVariable String city){
        return customersServices.findByCity(city);
    }

    @GetMapping("/type/{typeCustomers}")
    public List<CustomersModels> findByTypeCustomers(@PathVariable String typeCustomers){
        return customersServices.findByTypeCustomers(typeCustomers);
    }

    @GetMapping("/road/{road}")
    public List<CustomersModels> findByRoad(@PathVariable String road){
        return customersServices.findByRoad(road);
    }
}
