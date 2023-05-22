package com.tapanda.tapanda.controller.api.admin;

import com.tapanda.tapanda.responseDto.RoleAdminResponseDto;
import com.tapanda.tapanda.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/roles")
@Api(value = "관리자 권한 관리 Rest api")
@RequiredArgsConstructor
public class RolesAdminController {

    private final RoleService roleService;

    @GetMapping(value = "/all")
    @ApiOperation(value = "사용자 권한 목록 호출 api")
    public ResponseEntity<?> getRoleList() {
        List<RoleAdminResponseDto> roleDtoList = roleService.findAll().stream()
                .map(role -> new RoleAdminResponseDto(role)).collect(Collectors.toList());
        return ResponseEntity.ok(roleDtoList);
    }

}
