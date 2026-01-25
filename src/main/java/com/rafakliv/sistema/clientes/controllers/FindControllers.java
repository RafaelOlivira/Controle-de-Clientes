package com.rafakliv.sistema.clientes.controllers;

import com.rafakliv.sistema.clientes.CustomError.ErrorMessage;
import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Cacheable(value = "displayCustomers",key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    @GetMapping
    public ResponseEntity<Page<CustomersModels>> displayCustomers(Pageable pageable) {
        var customers = customersServices.displayCustomers(pageable);
        return ResponseEntity.ok(customers);
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

 /*   @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        try {
            var customer = customersServices.findByName(name);
            return ResponseEntity.ok(customer);
        }catch (NullPointerException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"NOT_FOUND");
            return ResponseEntity.status(404).body(errorMessage);
        }
    }*/

    @Cacheable(value = "findByCountry" , key = "#email + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' ")
    @GetMapping("/country/{country}")
    public ResponseEntity<Page<CustomersModels>> findByCountry(@PathVariable String country,Pageable pageable){
        var customers = customersServices.findByCountry(country,pageable);
        return ResponseEntity.ok(customers);
    }

    @Cacheable(value = "findByCity", key = "#city + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-'")
    @GetMapping("/city/{city}")
    public ResponseEntity<Page<CustomersModels>> findByCity(@PathVariable String city,Pageable pageable){
        var customers = customersServices.findByCity(city,pageable);
        return ResponseEntity.ok(customers);
    }

    @Cacheable(value = "findByTypeCustomers",key = "#typeCustomers + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-'")
    @GetMapping("/type/{typeCustomers}")
    public ResponseEntity<Page<CustomersModels>> findByTypeCustomers(@PathVariable String typeCustomers,Pageable pageable){
        var customers = customersServices.findByTypeCustomers(typeCustomers,pageable);
        return ResponseEntity.ok(customers);
    }

    @Cacheable(value = "findByRoad",key = "#road + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    @GetMapping("/road/{road}")
    public ResponseEntity<Page<CustomersModels>> findByRoad(@PathVariable String road, Pageable pageable){
        var customers = customersServices.findByRoad(road,pageable);
        return ResponseEntity.ok(customers);
    }
}
