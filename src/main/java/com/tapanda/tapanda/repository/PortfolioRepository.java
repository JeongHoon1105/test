package com.tapanda.tapanda.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tapanda.tapanda.model.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	
    static final String FIND_ALL_BY_USER_ID = "SELECT * FROM seller_portfolio a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";

    List<Portfolio> findAllBySellerId(Long sellerId);

    Optional<Portfolio> findByPortId(Long portId);

    Boolean existsByPortId(Long portId);
    
	@Query(value = FIND_ALL_BY_USER_ID, nativeQuery = true)
	List<Portfolio> findAllByUserId(@Param("userId") Long userId);
}
