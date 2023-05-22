package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Role;
import com.tapanda.tapanda.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class RoleAdminResponseDto {

    private String roleName;

    public RoleAdminResponseDto(Role role) {
        this.roleName = role.getName().toString();
    }
}
