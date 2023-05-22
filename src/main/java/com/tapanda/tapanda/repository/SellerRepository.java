package com.tapanda.tapanda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tapanda.tapanda.model.Seller;


public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByUserId(Long userId);

    Boolean existsByUserId(Long userId);

//    Optional<Seller> findById(Long sellerId);
//
//    Boolean existsById(String sellerId);

}
