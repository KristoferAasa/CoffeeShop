package com.example.coffeeshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coffeeshop.models.Department;



public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
