package com.rafakliv.sistema.clientes.repositories;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersModels, UUID> {

    Optional<CustomersModels> findByEmail(String email);

    CustomersModels findByUuid(UUID uuid);

    CustomersModels findByName(String name);

    List<CustomersModels> findByCountry(String country);

    List<CustomersModels> findByCity(String city);

    List<CustomersModels> findByTypeCustomers(String typeCustomers);

    List<CustomersModels> findByRoad(String road);
}
