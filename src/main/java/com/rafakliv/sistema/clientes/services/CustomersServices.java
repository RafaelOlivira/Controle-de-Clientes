package com.rafakliv.sistema.clientes.services;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomersServices {

    @Autowired
    private CustomersRepository customersRepository;

    // Register Customers
    public CustomersModels registerCustomers(CustomersModels customers) {
        if (customers.getTypeCustomers() == null || customers.getRoad() == null || customers.getName() == null || customers.getEmail() == null || customers.getCountry() == null
                || customers.getAge() == 0 || customers.getCity() == null) {
            throw new IllegalArgumentException("Fill in all the fields!");
        }
        return customersRepository.save(customers);
    }

    // Display Customers
    public List<CustomersModels> displayCustomers() {
        return customersRepository.findAll();
    }

    // Remove Customers
    public void removeCustomers(UUID uuid) {
        customersRepository.deleteById(uuid);
    }

    // Find By Email
    public Optional<CustomersModels> findByEmail(String email) {
        var customer = customersRepository.findByEmail(email);
        if (customer == null) {
            throw new NullPointerException("Customer not found");
        }
        return customer;
    }

    // Find By UUID
    public CustomersModels findByUuid(UUID uuid) {
        var customer = customersRepository.findByUuid(uuid);
        if (customer == null) {
            throw new NullPointerException("Customer not found");
        }
        return customer;
    }


    // Find By name
    public CustomersModels findByName(String name) {
        var customer = customersRepository.findByName(name);
        if (customer == null) {
            throw new NullPointerException("Customer not found");
        }
        return customer;
    }

    // Find By Country
    public List<CustomersModels> findByCountry(String country) {
        return customersRepository.findByCountry(country);
    }

    // Find By City
    public List<CustomersModels> findByCity(String city){
        return customersRepository.findByCity(city);
    }

    // Find By Type Customers
    public List<CustomersModels> findByTypeCustomers(String typeCustomers){
        return customersRepository.findByTypeCustomers(typeCustomers);
    }

    // Find By Road
    public List<CustomersModels> findByRoad(String road){
        return customersRepository.findByRoad(road);
    }
}
