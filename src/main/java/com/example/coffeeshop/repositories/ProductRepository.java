package com.example.coffeeshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coffeeshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.department.name = :departmentName")
    Page<Product> findByDepartmentName(@Param("departmentName") String departmentName, Pageable pageable);
}
