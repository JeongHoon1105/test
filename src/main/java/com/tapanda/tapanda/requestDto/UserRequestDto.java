package com.tapanda.tapanda.requestDto;

import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.model.Role;
import com.tapanda.tapanda.model.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "사용자 DTO")
@AllArgsConstructor
public class UserRequestDto {

	@NotBlank(message = "사용자 비밀번호을 입력해주세요.")
	@ApiModelProperty(value = "사용자 비밀번호", required = true, allowableValues = "String")
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()+|=-])(?=.*[0-9]).{8,25}", message = "8자리이상 25자이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.")
	private String password;

	@NotBlank(message = "사용자 이름을 입력해주세요.")
	@ApiModelProperty(value = "사용자 이름", required = true, allowableValues = "String")
	@Size(max = 40, message = "사용자 비밀번호 최대 문자는 40자입니다.")
	private String name;

	@NotBlank(message = "사용자 이메일을 입력해주세요.")
	@ApiModelProperty(value = "사용자 이메일", required = true, allowableValues = "String")
	private String email;

	@NotBlank(message = "사용자 주소을 입력해주세요.")
	@ApiModelProperty(value = "사용자 주소", required = true, allowableValues = "String")
	private String address;

	@NotBlank(message = "사용자 생일을 입력해주세요.")
	@ApiModelProperty(value = "사용자 생일을", required = true, allowableValues = "String")
	private String birth;

	@NotBlank(message = "사용자 성별을 입력해주세요.")
	@ApiModelProperty(value = "사용자 성별", required = true, allowableValues = "String")
	private String gender;

	private Set<String> roles;

	public User toEntity(Set<Role> roles, Instant birth) {
		// TODO Auto-generated method stub
		return User.builder().name(name).email(email).address(address).email(email).gender(Gender.valueOf(gender))
				.roles(roles).password(password).birth(birth).build();
	}

}