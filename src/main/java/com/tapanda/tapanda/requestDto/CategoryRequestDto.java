package com.tapanda.tapanda.requestDto;

import javax.validation.constraints.NotBlank;

import com.tapanda.tapanda.model.Category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "카테고리 DTO")
@AllArgsConstructor
public class CategoryRequestDto {
	
    @NotBlank(message = "카테고리 이름을 입력해주세요.")
    @ApiModelProperty(value = "카테고리 이름", required = true, allowableValues = "String")
    private String name;

    @NotBlank(message = "카테고리 코드를 입력해주세요.")
    @ApiModelProperty(value = "카테고리 코드", required = true, allowableValues = "String")
    private String code;

    @ApiModelProperty(value = "카테고리 부모코드", required = false, allowableValues = "String")
    private String parentCode;

    public Category toEntity(Long seq) {
        // TODO Auto-generated method stub
        return Category.builder().name(name).code(code).parentCode(parentCode).seq(seq).build();
    }
}