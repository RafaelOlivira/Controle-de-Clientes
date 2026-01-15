package com.rafakliv.sistema.clientes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomersModels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String typeCustomers;

    @Column(nullable = false)
    private String road;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

}
