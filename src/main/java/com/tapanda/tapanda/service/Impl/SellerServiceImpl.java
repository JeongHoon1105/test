package com.tapanda.tapanda.service.Impl;

import java.util.Optional;

import com.tapanda.tapanda.service.*;
import org.springframework.stereotype.Service;

import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.repository.SellerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

//    @Override
//    public Seller save(Seller entity) {
//        return sellerRepository.save(entity);
//    }

    @Override
    public Optional<Seller> findByUserId(Long userId) {
        if (!existsByUserId(userId)) {
            throw new UserNotFoundException("해당 판매자를 찾을수 없습니다.", null, null);
        }
        return sellerRepository.findByUserId(userId);
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return sellerRepository.existsByUserId(userId);
    }

    @Override
    public Seller save(Seller entity) {
        // TODO Auto-generated method stub
        return sellerRepository.save(entity);
    }

    @Override
    public void deleteById(Long userId) {
        if (!existsByUserId(userId)) {
            throw new UserNotFoundException("해당 사용자를 찾을수 없습니다.", null, null);
        }
        sellerRepository.deleteById(userId);
    }


//    @Override
//    public Optional<Seller> findById(String id) {
//        if (!existsById(id)) {
//            throw new UserNotFoundException("해당 판매자를 찾을수 없습니다.", null, null);
//        }
//        return sellerRepository.findById(Long.parseLong(id));
//    }
//
//    @Override
//    public Boolean existsById(String id) {
//        return sellerRepository.existsById(id);
//    }
}
