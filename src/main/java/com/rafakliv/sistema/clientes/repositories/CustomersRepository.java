package com.rafakliv.sistema.clientes.repositories;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersModels, UUID> {

}
