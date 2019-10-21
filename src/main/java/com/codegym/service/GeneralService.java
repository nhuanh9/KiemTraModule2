package com.codegym.service;


import java.util.Collection;

public interface GeneralService<T> {
    Iterable<T> findAll();

    T findById(Long id);

    void save(T model);

    void remove(Long id);
}