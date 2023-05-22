package com.tapanda.tapanda.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tapanda.tapanda.enum_.RoleName;
import com.tapanda.tapanda.exception.RoleNotFoundException;
import com.tapanda.tapanda.model.Role;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.repository.RoleRepository;
import com.tapanda.tapanda.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository roleRepository;
	
	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public Optional<Role> findByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(RoleName.valueOf(name));
	}

	@Override
	public Boolean existsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Role> deserialize(String roles) {
		// TODO Auto-generated method stub
		roles = roles.replace("[", "");
		roles = roles.replace("]", "");
		roles = roles.replace("'\"\'","");
 
		return Arrays.stream(roles.split(",")).map(name -> {
			final Role role = findByName(name)
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
			return role;
		}).collect(Collectors.toSet());
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}