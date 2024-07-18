package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.Manufacturer;
import com.example.coffeeshop.models.Supplier;
import com.example.coffeeshop.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping
    public String listManufacturers(Model model) {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers/list";
    }

    @GetMapping("/{id}")
    public String getManufacturer(@PathVariable Long id, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).get();
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/manufacturer";
    }

    @GetMapping("/add")
    public String addManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturers/add";
    }

    @PostMapping
    public String addManufacturer(@ModelAttribute Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/edit/{id}")
    public String editManufacturerForm(@PathVariable Long id, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).get();
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/edit";
    }

    @PostMapping("/edit/{id}")
    public String editManufacturer(@PathVariable Long id, @ModelAttribute Manufacturer manufacturer) {

        Manufacturer existingManufacturer = manufacturerRepository.findById(id).get();

        existingManufacturer.setName(manufacturer.getName());
        existingManufacturer.setUrl(manufacturer.getUrl());

        manufacturerRepository.save(existingManufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        manufacturerRepository.deleteById(id);
        return "redirect:/manufacturers";
    }
}