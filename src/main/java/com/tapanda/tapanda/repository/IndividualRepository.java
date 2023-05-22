package com.tapanda.tapanda.repository;

import com.tapanda.tapanda.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IndividualRepository extends JpaRepository<Individual, Long> {

    Optional<Individual> findById(Long id);

    List<Individual> findAll();

    Boolean existsBySellerId(Long sellerId);

    static final String FIND_BY_UID = "SELECT A.* FROM INDIVIDUAL_INFO A "
            + "INNER JOIN SELLER B ON B.SELLER_ID = A.SELLER_ID "
            + "INNER JOIN USER C ON C.USER_ID = B.USER_ID "
            + "WHERE C.UID = :uid";

    @Query(value = FIND_BY_UID, nativeQuery = true)
    Optional<Individual> findByUid(@Param("uid") String uid);
}
