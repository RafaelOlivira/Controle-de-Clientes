package com.rafakliv.sistema.clientes.controllers;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @PostMapping("/new/create")
    public String createCustomers(@ModelAttribute("customer") CustomersModels customer){
        customersServices.registerCustomers(customer);
        return "redirect:/home";
    }


}
