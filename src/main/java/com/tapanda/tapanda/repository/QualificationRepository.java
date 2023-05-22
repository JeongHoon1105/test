package com.tapanda.tapanda.repository;

import com.tapanda.tapanda.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
	
    static final String FIND_ALL_BY_USER_ID = "SELECT * FROM seller_qualification a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";
    
    static final String COUNT_BY_USER_ID = "SELECT COUNT(*) FROM seller_qualification a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";
    

    List<Qualification> findAllBySellerId(Long sellerId);
    
    @Query(value = FIND_ALL_BY_USER_ID, nativeQuery = true)
    List<Qualification> findAllByUserId(@Param("userId") Long userId);
    
    @Query(value = COUNT_BY_USER_ID, nativeQuery = true)
    Integer countByUserId(@Param("userId") Long userId);

    Optional<Qualification> findByQualId(Long qualId);

    Boolean existsByQualId(Long qualId);

}
