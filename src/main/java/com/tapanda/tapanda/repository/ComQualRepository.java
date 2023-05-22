package com.tapanda.tapanda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tapanda.tapanda.model.Company;

public interface ComQualRepository extends JpaRepository<Company, Long> {

    static final String FIND_ALL_BY_USER_ID = "SELECT * FROM seller_company_qualification a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";
    
    static final String COUNT_BY_USER_ID = "SELECT COUNT(*) FROM seller_company_qualification a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";

    List<Company> findAllBySellerId(Long sellerId);

    Optional<Company> findByComQualId(Long comQualid);

    Boolean existsByComQualId(Long comQualid);
    
    @Query(value = COUNT_BY_USER_ID, nativeQuery = true)
    Integer countByUserId(Long userId);
    
    @Query(value = FIND_ALL_BY_USER_ID, nativeQuery = true)
    List<Company> findAllByUserId(@Param("userId") Long userId);


}
