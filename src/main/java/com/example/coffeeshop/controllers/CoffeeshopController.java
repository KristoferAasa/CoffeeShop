package com.example.coffeeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.coffeeshop.models.Product;
import com.example.coffeeshop.repositories.ProductRepository;

@Controller
public class CoffeeshopController {

    @Autowired
    private ProductRepository productRepository;

    private static final int PAGE_SIZE = 18;

    @GetMapping("/")
    public String getHome(Model model) {
        return "index";
    }

    @GetMapping("/kahvilaitteet")
    public String getKahvilaitteet(
            @RequestParam(defaultValue = "0") int sivu,
            Model model) {
        Pageable pageable = PageRequest.of(sivu, PAGE_SIZE, Sort.by("name").descending());
        Page<Product> filteredProductsPage = productRepository.findByDepartmentName("Kahvilaitteet", pageable);
        model.addAttribute("products", filteredProductsPage.getContent());
        model.addAttribute("currentPage", sivu);
        model.addAttribute("totalPages", filteredProductsPage.getTotalPages());
        return "coffeeshop/kahvilaitteet";
    }

    @GetMapping("/kulutustuotteet")
    public String getKulutustuotteet(
            @RequestParam(defaultValue = "0") int sivu,
            Model model) {
        Pageable pageable = PageRequest.of(sivu, PAGE_SIZE, Sort.by("name"));
        Page<Product> filteredProductsPage = productRepository.findByDepartmentName("Kulutustuotteet", pageable);
        model.addAttribute("products", filteredProductsPage.getContent());
        model.addAttribute("currentPage", sivu);
        model.addAttribute("totalPages", filteredProductsPage.getTotalPages());
        return "coffeeshop/kulutustuotteet";
    }

    @GetMapping("/tuote/{id}")
    public String getTuote(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "coffeeshop/tuote";
    }
}