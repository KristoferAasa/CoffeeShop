package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.Product;
import com.example.coffeeshop.repositories.DepartmentRepository;
import com.example.coffeeshop.repositories.ManufacturerRepository;
import com.example.coffeeshop.repositories.ProductRepository;
import com.example.coffeeshop.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "products/product";
    }

    @GetMapping("/test")
    @ResponseBody
    public String getTest() {
        Product newProduct = new Product();
        newProduct.setDepartment(departmentRepository.findById(1L).get());
        newProduct.setDescription("Product description");
        newProduct.setImage("https://placehold.co/400x400?text=placeholder");
        newProduct.setManufacturer(manufacturerRepository.findById(1L).get());
        newProduct.setName("Product title");
        newProduct.setPrice(BigDecimal.valueOf(9.99));
        newProduct.setSupplier(supplierRepository.findById(1L).get());

        productRepository.save(newProduct);
        return "test product added";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "products/add";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product, @RequestParam Long departmentId,
            @RequestParam Long manufacturerId, @RequestParam Long supplierId) {
        product.setDepartment(departmentRepository.findById(departmentId).get());
        product.setManufacturer(manufacturerRepository.findById(manufacturerId).get());
        product.setSupplier(supplierRepository.findById(supplierId).get());
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product, @RequestParam Long departmentId,
            @RequestParam Long manufacturerId, @RequestParam Long supplierId) {
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setDepartment(departmentRepository.findById(departmentId).get());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImage(product.getImage());
        existingProduct.setManufacturer(manufacturerRepository.findById(manufacturerId).get());
        existingProduct.setSupplier(supplierRepository.findById(supplierId).get());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}