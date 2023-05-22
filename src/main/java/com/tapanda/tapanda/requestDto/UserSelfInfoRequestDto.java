package com.tapanda.tapanda.requestDto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "사용자 DTO")
@AllArgsConstructor
public class UserSelfInfoRequestDto {
	
	private String username;
	
	private String selfIntro;
	
	private String shedule;
	
	private String cPhrase;
	
	private String jobStatus;
}
