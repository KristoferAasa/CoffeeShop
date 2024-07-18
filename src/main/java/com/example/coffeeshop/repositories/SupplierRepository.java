package com.example.coffeeshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coffeeshop.models.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
