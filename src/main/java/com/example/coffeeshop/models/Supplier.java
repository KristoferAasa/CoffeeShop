package com.example.coffeeshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Supplier extends AbstractPersistable<Long> {
    private String name;
    private String contactPerson;
    private String contactEmail;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
