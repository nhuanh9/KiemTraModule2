package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Product.class)
    private Collection<Product> products;
}
