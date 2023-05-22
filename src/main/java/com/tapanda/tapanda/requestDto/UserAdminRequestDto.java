package com.tapanda.tapanda.requestDto;

import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.tapanda.tapanda.annotation.EmailDuplicate;
import com.tapanda.tapanda.annotation.NameDuplicate;
import com.tapanda.tapanda.annotation.RegisterMatchPassword;
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
@RegisterMatchPassword
@ApiModel(value = "사용자 DTO")
@AllArgsConstructor
public class UserAdminRequestDto {

	@NotBlank(message = "사용자 비밀번호을 입력해주세요.")
	@ApiModelProperty(value = "사용자 비밀번호", required = true, allowableValues = "String")
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()+|=-])(?=.*[0-9]).{8,25}", message = "8자리이상 25자이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.")
	private String password;
	
	@NotBlank(message = "사용자 비밀번호을 다시 입력해주세요.")
	@ApiModelProperty(value = "사용자 비밀번호", required = true, allowableValues = "String")
	private String passwordCheck;

	@NotBlank(message = "사용자 이름을 입력해주세요.")
	@ApiModelProperty(value = "사용자 이름", required = true, allowableValues = "String")
	@Size(max = 40, message = "사용자 비밀번호 최대 문자는 40자입니다.")
	@NameDuplicate
	private String name;

	@NotBlank(message = "사용자 이메일을 입력해주세요.")
	@ApiModelProperty(value = "사용자 이메일", required = true, allowableValues = "String")
	@Email
	@EmailDuplicate
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
	
	@ApiModelProperty(value = "사용자 전화 번호", required = false, allowableValues = "String")
	private String phone;
	
	private Boolean emailConfim;

	private Set<String> roles;

	public User toEntity(Set<Role> roles, Instant birth) {
		// TODO Auto-generated method stub
		return User.builder().name(name).email(email).address(address).email(email).gender(Gender.valueOf(gender))
				.roles(roles).password(password).birth(birth).emailConfim(emailConfim).phone(phone).build();
	}

}