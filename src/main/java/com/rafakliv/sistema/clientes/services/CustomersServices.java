package com.rafakliv.sistema.clientes.services;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<CustomersModels> displayCustomers(Pageable pageable) {
        return customersRepository.findAll(pageable);
    }

    // Update customer
    public void updateCustomer(CustomersModels customersModels){
        customersRepository.save(customersModels);
    }

    // Remove Customers
    public void removeCustomers(UUID uuid) {
        customersRepository.deleteById(uuid);
    }

    // Find By Email
    public Page<CustomersModels> findByEmail(String email,Pageable pageable) {
        var customer = customersRepository.findByEmail(email,pageable);
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
  /*  public CustomersModels findByName(String name) {
        var customer = customersRepository.findByName(name);
        if (customer == null) {
            throw new NullPointerException("Customer not found");
        }
        return customer;
    }*/

    // Find By Name com possiveis resultados maiores que 1
    public Page<CustomersModels> findByNames(String name, Pageable pageable){
        Page<CustomersModels> customersModelsList = customersRepository.findByName(name,pageable);
        if(customersModelsList == null){
            throw new NullPointerException("Customer not found");
        }
        return customersModelsList;
    }

    // Find By Country
    public Page<CustomersModels> findByCountry(String country, Pageable pageable) {
        return customersRepository.findByCountry(country,pageable);
    }

    // Find By City
    public Page<CustomersModels> findByCity(String city,Pageable pageable){
        return customersRepository.findByCity(city,pageable);
    }

    // Find By Type Customers
    public Page<CustomersModels> findByTypeCustomers(String typeCustomers,Pageable pageable){
        return customersRepository.findByTypeCustomers(typeCustomers,pageable);
    }

    // Find By Road
    public Page<CustomersModels> findByRoad(String road,Pageable pageable){
        return customersRepository.findByRoad(road,pageable);
    }
}
