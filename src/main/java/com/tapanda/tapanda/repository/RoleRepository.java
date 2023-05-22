package com.tapanda.tapanda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tapanda.tapanda.enum_.RoleName;
import com.tapanda.tapanda.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName name);
	
	Boolean existsByName(String name);
    
}