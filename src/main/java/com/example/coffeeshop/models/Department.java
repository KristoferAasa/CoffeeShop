package com.example.coffeeshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends AbstractPersistable<Long> {
    private String name;
    private Long departmentId;

    @OneToMany(mappedBy = "department")
    private List<Product> products;
}