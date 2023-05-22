package com.tapanda.tapanda.responseDto;

import java.util.Set;
import java.util.stream.Collectors;

import com.tapanda.tapanda.model.Role;
import com.tapanda.tapanda.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAdminResponseDto {

	private Long id;

	private String uid;

	private String password;

	private String name;

	private String email;

	private String birth;

	private String gender;

	private String address;

	private Boolean emailConfim;

	private String imageThumb;

	private String imageCover;

	private String jobStatus;

	private String selfIntro;

	private String cPhrase;

	private String schedule;

	private Set<String> roles;

	public UserAdminResponseDto(User user) {
		// TODO Auto-generated constructor stub
		this.id = user.getId();
		this.uid = user.getUid().toString();
		this.name = user.getName();
		this.address = user.getAddress();
		this.password = user.getPassword();
		this.emailConfim = user.getEmailConfim();
		if (user.getBirth() != null) {
			this.birth = user.getBirth().toString();
		}
		this.email = user.getEmail();
		if (user.getGender() != null) {
			this.gender = user.getGender().toString();
		}
		this.roles = user.getRoles().stream().map(Role::getName).map(role -> role.toString())
				.collect(Collectors.toSet());
		if (user.getBirth() != null) {
			this.birth = user.getBirth().toString();
		}
		if (user.getImageCover() != null) {
			this.imageCover = user.getImageCover().getUrl();
		}
		if (user.getImageThumb() != null) {
			this.imageThumb = user.getImageThumb().getUrl();
		}
		if (user.getSeller() != null) {
			this.schedule = user.getSeller().getSchedule();
			this.cPhrase = user.getSeller().getCPhrase();
			this.selfIntro = user.getSeller().getSelfIntro();
			if (user.getSeller().getJobStatus() != null) {
				this.jobStatus = user.getSeller().getJobStatus().toString();
			}
		}
	}

}