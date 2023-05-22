package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.tapanda.tapanda.model.Role;

public interface RoleService {

	Role save(Role role);

	Optional<Role> findByName(String name);

	Boolean existsByName(String name);
	
	Set<Role> deserialize(String roles);

	List<Role> findAll();
}