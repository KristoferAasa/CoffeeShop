package com.example.coffeeshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer extends AbstractPersistable<Long> {
    private String name;
    private String url;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;
}