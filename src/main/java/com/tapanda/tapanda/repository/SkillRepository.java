package com.tapanda.tapanda.repository;

import com.tapanda.tapanda.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
	
    static final String FIND_ALL_BY_USER_ID = "SELECT * FROM SELLER_SKILL a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";
    
    static final String COUNT_BY_USER_ID = "SELECT COUNT(*) FROM SELLER_SKILL a"
    		+ "    WHERE a.seller_id = ("
    		+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId"
    		+ "    )";
    

    List<Skill> findAllBySellerId(Long sellerId);

    Optional<Skill> findBySkillId(Long skillId);

    Boolean existsBySkillId(Long skillId);
    
    @Query(value = COUNT_BY_USER_ID, nativeQuery = true)
    Integer countByUserId(Long userId);
    
    @Query(value = FIND_ALL_BY_USER_ID, nativeQuery = true)
    List<Skill> findAllByUserId(@Param("userId") Long userId);


}
