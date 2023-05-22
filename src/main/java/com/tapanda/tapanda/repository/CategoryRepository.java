package com.tapanda.tapanda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tapanda.tapanda.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	static final String FIND_ID_BY_CATEGORY_CODE = "SELECT service_category_id FROM service_category "
												 + "WHERE category_code = :categoryCode";

	Optional<Category> findByCode(String code);

	Boolean existsByCode(String code);

	Optional<Category> findFirstByParentCodeOrderBySeqDesc(String parentCode);
	
	 void deleteByCode(String code);

	@Query(value = FIND_ID_BY_CATEGORY_CODE, nativeQuery = true)
	Long findIdByCategoryCode(@Param("categoryCode") String categoryCode);
}