package com.rafakliv.sistema.clientes.repositories;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersModels, UUID> {

    Optional<CustomersModels> findByEmail(String email);

    CustomersModels findByUuid(UUID uuid);

  /*  CustomersModels findByName(String name);*/

    Page<CustomersModels> findByName(String name, Pageable pageable);


    Page<CustomersModels> findByCountry(String country, Pageable pageable);

    Page<CustomersModels> findByCity(String city,Pageable pageable);

    Page<CustomersModels> findByTypeCustomers(String typeCustomers,Pageable pageable);

    Page<CustomersModels> findByRoad(String road,Pageable pageable);
    

}
