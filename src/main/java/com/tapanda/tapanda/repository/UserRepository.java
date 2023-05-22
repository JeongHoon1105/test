package com.tapanda.tapanda.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tapanda.tapanda.model.Role;
import com.tapanda.tapanda.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    static final String FIND_SELLER_ROLE_BY_USER_ID = "SELECT IF (COUNT(*) > 0, \"true\", \"false\") "
    		+ "FROM user_roles AS A "
    		+ "INNER JOIN role AS B "
    		+ "ON B.role_id = A.roles_role_id "
    		+ "INNER JOIN tapanda.seller AS C "
    		+ "ON C.user_id = A.users_user_id "
    		+ "WHERE A.users_user_id = :userId AND B.name = \"ROLE_SELLER\"";

	Optional<User> findByUid(UUID uid);

	Optional<User> findByName(String name);
	
	Optional<User> findByEmail(String email);

	Boolean existsByUid(UUID uid);

	Boolean existsByEmail(String email);
	
	@Query(value = FIND_SELLER_ROLE_BY_USER_ID, nativeQuery = true)
	Boolean isSeller(@Param("userId") Long userId);
	
	List<User> findAllByRoles(Role role);
	
	List<User> findByNameContainingOrEmailContaining(String word, String word1);

	Boolean existsByName(String name);

}