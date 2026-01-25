package com.rafakliv.sistema.clientes.controllers;

import com.rafakliv.sistema.clientes.models.CustomersModels;
import com.rafakliv.sistema.clientes.services.CustomersServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/home")
public class TemplateController {

    @Autowired
    private CustomersServices customersServices;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("customer", new CustomersModels());
        return "index";
    }

    @GetMapping("/customers")
    public String customers(Model model, @PageableDefault(size = 10, sort = "uuid") Pageable pageable) {
        Page<CustomersModels> customersPage = customersServices.displayCustomers(pageable);
        model.addAttribute("customersPage", customersPage);
        model.addAttribute("customers", customersPage.getContent());
        return "customers";
    }

    @PostMapping("/new/create")
    public String createCustomers(@Valid CustomersModels customer,BindingResult result,Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer",customer);
            model.addAttribute("errorFormulario",true);
            return "index";
        }

        customersServices.registerCustomers(customer);
        return "redirect:/home";
    }

    // Update Customer
    @PostMapping("/confirmar/atualizacao")
    public String confirmarAtualizacao(CustomersModels customersModels){
        System.out.println(customersModels.getUuid());
        customersServices.updateCustomer(customersModels);
        return "redirect:/home/customers";
    }
    @PostMapping("/atualizar/id")
    public String atualizarId(@RequestParam(required = false) UUID customerId,Model model,@PageableDefault(size = 10, sort = "uuid") Pageable pageable){
        if (customerIdNull(customerId, model, pageable)) return "customers";

        CustomersModels customersSelect = customersServices.findByUuid(customerId);
        model.addAttribute("customer",customersSelect);
        return "update";
    }

    private boolean customerIdNull(@RequestParam(required = false) UUID customerId, Model model, @PageableDefault(size = 10, sort = "uuid") Pageable pageable) {
        if(customerId == null){
            Page<CustomersModels> customersPage = customersServices.displayCustomers(pageable);
            model.addAttribute("error","Selecione um cliente!");
            model.addAttribute("customersPage", customersPage);
            model.addAttribute("customers", customersPage.getContent());
            return true;
        }
        return false;
    }

    @PostMapping("/delete/id")
    public String deleteById(@RequestParam(required = false) UUID customerId,Model model,@PageableDefault(size = 10, sort = "uuid") Pageable pageable){
        if (customerIdNull(customerId, model, pageable)) return "customers";
        customersServices.removeCustomers(customerId);
        return "redirect:/home/customers";
    }

    @PostMapping("/busca/nome")
    public String findByName(@RequestParam("busca") String name,Model model, @PageableDefault(size = 10, sort = "uuid") Pageable pageable){
        if(name == null){
            return "redirect:/home/customers";
        }

        Page<CustomersModels> customersModelsList = customersServices.findByNames(name,pageable);

        Page<CustomersModels> customersPage = customersServices.displayCustomers(pageable);
        model.addAttribute("customersPage", customersPage);
        model.addAttribute("customers", customersPage.getContent());
        model.addAttribute("customers",customersModelsList);
        return "customers";
    }
    @PostMapping("/busca/tipo")
    public String findByTypeCustomers(@RequestParam("tipo") String tipo, Model model, @PageableDefault(size = 10,sort = "uuid") Pageable pageable){
        if(tipo.equals("Ambos")){
            Page<CustomersModels> customersModelsPage = customersServices.displayCustomers(pageable);
            Page<CustomersModels> customersPage = customersServices.displayCustomers(pageable);
            model.addAttribute("customersPage", customersPage);
            model.addAttribute("customers", customersPage.getContent());
            model.addAttribute("customers",customersModelsPage);
            return "customers";
        }
        Page<CustomersModels> customersModelsPage = customersServices.findByTypeCustomers(tipo,pageable);
        Page<CustomersModels> customersPage = customersServices.displayCustomers(pageable);

        model.addAttribute("customersPage", customersPage);
        model.addAttribute("customers", customersPage.getContent());
        model.addAttribute("customers",customersModelsPage);
        model.addAttribute("tipo",tipo);
        return "customers";
    }
}
