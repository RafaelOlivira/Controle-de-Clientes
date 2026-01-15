package com.rafakliv.sistema.clientes.services;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.repositories.CustomersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CustomersServices {

    @Autowired
    private CustomersRepository customersRepository;

    // Register Customers
    public CustomersModels registerCustomers(CustomersModels customers){
        if(customers.getTypeCustomers() == null || customers.getRoad() == null || customers.getName() == null || customers.getEmail() == null || customers.getCountry() == null
        || customers.getAge() == 0 || customers.getCity() == null){
            throw new IllegalArgumentException("Fill in all the fields!");
        }
        return customersRepository.save(customers);
    }

    // Display Customers
    public List<CustomersModels> displayCustomers(){
        return customersRepository.findAll();
    }

    // Remove Customers
    public void removeCustomers(UUID uuid){
        customersRepository.deleteById(uuid);
    }
}
