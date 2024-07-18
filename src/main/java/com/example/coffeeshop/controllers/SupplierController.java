package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.Supplier;
import com.example.coffeeshop.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public String listSuppliers(Model model) {
        List<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        return "suppliers/list";
    }

    @GetMapping("/{id}")
    public String getSupplier(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", supplierRepository.findById(id).get());
        return "suppliers/supplier";
    }
    @GetMapping("/add")
    public String addSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/add";
    }

    @PostMapping
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String editSupplierForm(@PathVariable Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id).get();
        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSupplier(@PathVariable Long id, @ModelAttribute Supplier supplier) {
        Supplier existingSupplier = supplierRepository.findById(id).get();
        
        existingSupplier.setName(supplier.getName());
        existingSupplier.setContactPerson(supplier.getContactPerson());
        existingSupplier.setContactEmail(supplier.getContactEmail());
        
        supplierRepository.save(existingSupplier);
        
        return "redirect:/suppliers/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierRepository.deleteById(id);
        return "redirect:/suppliers";
    }
}