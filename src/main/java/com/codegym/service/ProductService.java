package com.codegym.service;

import com.codegym.model.Product;

public interface ProductService extends GeneralService<Product> {
    Iterable<Product> findByNameContains(String name);
}
