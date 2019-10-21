package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @ManyToOne
    private ProductType productType;

    private String price;

    private String amount;

    private Date date;

    private String description;
}
