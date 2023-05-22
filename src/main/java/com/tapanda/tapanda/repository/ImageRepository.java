package com.tapanda.tapanda.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tapanda.tapanda.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Boolean existsByIid(UUID iid);

    @Transactional
    void deleteByIid(UUID iid);

    Optional<Image> findByIid(UUID filename);
}