package com.example.coffeeshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coffeeshop.models.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
