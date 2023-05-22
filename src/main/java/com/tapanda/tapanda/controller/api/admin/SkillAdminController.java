package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.SkillUpdateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tapanda.tapanda.exception.SkillRegisterException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Skill;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.SkillRequestDto;
import com.tapanda.tapanda.responseDto.SkillAdminResponseDto;
import com.tapanda.tapanda.service.SkillService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/seller_skill")
@Api(value = "관리자 판매자 스킬 관리 Rest api")
@RequiredArgsConstructor
public class SkillAdminController {

	private final SkillService skillService;

	@PostMapping("/{user_id}/register")
    @ApiOperation(value = "스킬를 등록한다.")
    public ResponseEntity<?> registerSkill(
            @ApiParam(value = "스킬 DTO") @Valid @RequestBody SkillRequestDto request, @PathVariable("user_id") Long userId) {
        return skillService.registerSkill(request, userId).map(skill -> {
            return ResponseEntity.ok(new ApiResponse(true, "스킬 등록에 성공하였습니다."));
        }).orElseThrow(() -> new SkillRegisterException(request.getTitle(), "스킬 등록에 실패하였습니다.", null));

    }

    @GetMapping("/{seller_skill_id}")
    @ApiOperation(value = "판매자 스킬 조회 api")
    public ResponseEntity<?> getSkill(@PathVariable("seller_skill_id")Long skillId) {
        Skill skill = skillService.findBySkillId(skillId)
                .orElseThrow(() -> new UserNotFoundException("해당 스킬을 찾을 수 없습니다.", null, null));
        SkillAdminResponseDto skillDto = new SkillAdminResponseDto(skill);
        return ResponseEntity.ok(skillDto);
    }

    @PutMapping(value = "/{seller_skill_id}")
    @ApiOperation(value = "판매자 스킬 수정 api")
    public ResponseEntity<?> skillUpdate(@ApiParam(value = "스킬 DTO") @Valid @RequestBody SkillRequestDto request,
            @PathVariable("seller_skill_id") Long skillId) {
        return skillService.updateSkill(request, skillId).map(skill -> {
            return ResponseEntity.ok(new ApiResponse(true, "스킬 수정에 성공하였습니다."));
        }).orElseThrow(() -> new SkillUpdateException(request.getTitle(), "스킬 수정에 실패하였습니다.", null));
    }

    @DeleteMapping(value = "/{skill_id}")
    @ApiOperation(value = "프리랜서 스킬 삭제 api")
    public ResponseEntity<?> skillDelete(@PathVariable("skill_id") Long skillId) {
        skillService.deleteById(skillId);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 스킬 삭제완료"));
    }
}
