package com.tapanda.tapanda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tapanda.tapanda.model.CorporationInfo;

public interface CorpInfoRepository extends JpaRepository<CorporationInfo, Long> {
	
    static final String FIND_BY_USER_ID = "SELECT * FROM seller_corporation_info a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";
    
    @Query(value = FIND_BY_USER_ID, nativeQuery = true)
	Optional<CorporationInfo> findByUserId(@Param("userId") Long userId);
}