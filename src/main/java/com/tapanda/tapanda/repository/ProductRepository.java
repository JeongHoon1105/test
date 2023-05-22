package com.tapanda.tapanda.repository;

import com.tapanda.tapanda.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}