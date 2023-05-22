package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.model.Individual;
import com.tapanda.tapanda.model.Seller;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@ApiModel(value = "프리랜서 출품정보 DTO")
public class IndividualAdminRequestDto {

    @NotBlank(message = "출품자 이름을 입력해주세요.")
    @ApiModelProperty(value = "출품자 이름", required = true, allowableValues = "String")
    private String name;

    @NotBlank(message = "출품자 주소를 입력해주세요.")
    @ApiModelProperty(value = "출품자 주소", required = true, allowableValues = "String")
    private String address;

    @NotBlank(message = "출품자 생년월일을 입력해주세요.")
    @ApiModelProperty(value = "출품자 생년월일", required = true, allowableValues = "String")
    private String birth;

    @NotBlank(message = "출품자 성별을 입력해주세요.")
    @ApiModelProperty(value = "출품자 성별", required = true, allowableValues = "String")
    private String gender;

    @NotBlank(message = "출품제품의 분류를 입력해주세요.")
    @ApiModelProperty(value = "출품제품 분류", required = true, allowableValues = "String")
    private String classitication;

    public Individual toEntity(Seller seller, Instant birth) {
        // TODO Auto-generated method stub
        return Individual.builder().seller(seller).name(name).address(address).gender(Gender.valueOf(gender)).birth(birth)
                .classitication(classitication).build();
    }
}
