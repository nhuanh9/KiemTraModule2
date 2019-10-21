package com.codegym.service.impl;

import com.codegym.model.ProductType;
import com.codegym.repository.ProductTypeRepository;
import com.codegym.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public Iterable<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeRepository.findOne(id);
    }

    @Override
    public void save(ProductType model) {
        productTypeRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        productTypeRepository.delete(id);
    }
}
