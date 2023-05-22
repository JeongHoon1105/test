package com.tapanda.tapanda.service;

import com.tapanda.tapanda.model.Seller;

import java.util.Optional;

public interface SellerService {

    Seller save(Seller entity);

    Optional<Seller> findByUserId(Long userId);

    Boolean existsByUserId(Long userId);

    void deleteById(Long userId);

//    void deleteByUserId(Long userId);


//    Optional<Seller> findById(String sellerId);
//
//    Boolean existsById(String sellerId);
}
