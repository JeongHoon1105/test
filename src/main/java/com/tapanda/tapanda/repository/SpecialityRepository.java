package com.tapanda.tapanda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tapanda.tapanda.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

	static final String FIND_ALL_BY_USER_ID = "SELECT * FROM seller_specialities a" + "    WHERE a.seller_id = ("
			+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId" + "    )";

	static final String COUNT_BY_USER_ID = "SELECT COUNT(*) FROM seller_specialities a" + "    WHERE a.seller_id = ("
			+ "        SELECT seller_id FROM SELLER b WHERE b.user_id = :userId" + "    )";

	List<Speciality> findAllBySellerId(Long sellerId);

	@Query(value = FIND_ALL_BY_USER_ID, nativeQuery = true)
	List<Speciality> findAllByUserId(@Param("userId") Long userId);
	
	@Query(value = COUNT_BY_USER_ID, nativeQuery = true)
	Integer countAllByUserId(@Param("userId") Long userId);

	Optional<Speciality> findBySpecId(Long specId);

	Boolean existsBySpecId(Long specId);
}
