package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.Department;
import com.example.coffeeshop.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "departments/list";
    }

    @GetMapping("/add")
    public String addDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/add";
    }

    @PostMapping
    public String addDepartment(@ModelAttribute Department department){
        departmentRepository.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        departmentRepository.deleteById(id);
        return "redirect:/departments";
    }
}
