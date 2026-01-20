package com.rafakliv.sistema.clientes.controllers;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class TemplateController {

    @Autowired
    private CustomersServices customersServices;

    @GetMapping()
    public String home(Model model){
        model.addAttribute("customer", new CustomersModels());
        return "index";
    }
    @GetMapping("/customers")
    public String customers(Model model, @PageableDefault(size = 10,sort="uuid") Pageable pageable){
        Page<CustomersModels> customersPage = customersServices.displayCustomers(pageable);
        model.addAttribute("customersPage", customersPage);
        model.addAttribute("customers",customersPage.getContent());
        return "customers";
    }

    @GetMapping("/customers/search")
    public String customersFindEmail(Model model, @RequestParam String email){
        Optional<CustomersModels> customersModels = customersServices.findByEmail(email);
        model.addAttribute("customer",customersModels);
        return "redirect:/customers";
    }
    @PostMapping("/new/create")
    public String createCustomers(@ModelAttribute("customer") CustomersModels customer){
        customersServices.registerCustomers(customer);
        return "redirect:/home";
    }



}
