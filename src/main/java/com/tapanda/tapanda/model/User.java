package com.tapanda.tapanda.model;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tapanda.tapanda.enum_.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", allocationSize = 1)
	private Long id;

	@Column(name = "uid", columnDefinition = "varchar(36)", unique = true)
	@Type(type = "uuid-char")
	private UUID uid;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "EMAIL_CONFIM")
	private Boolean emailConfim;

	@Column(name = "BIRTH", nullable = false)
	private Instant birth;

	@Column(name = "GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THUMBNAIL_ID")
	private Image imageThumb;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COVER_ID")
	private Image imageCover;

	@Column(name = "PHONE")
	private String phone;

	@OneToOne(mappedBy = "user")
	private Seller seller;

	@OneToMany(mappedBy = "user")
	private List<Notice> notice;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Role> roles;

	@Builder
	public User(String password, String address, String name, String email, Boolean emailConfim,
			Instant birth, Gender gender, Image imageThumb, Image imageCover, Seller seller, String phone,
			Set<Role> roles) {
		super();
		this.uid = UUID.randomUUID();
		this.password = password;
		this.address = address;
		this.name = name;
		this.email = email;
		this.emailConfim = emailConfim;
		this.birth = birth;
		this.gender = gender;
		this.imageThumb = imageThumb;
		this.imageCover = imageCover;
		this.seller = seller;
		this.roles = roles;
		this.phone = phone;
	}

	public void updateBasicAdmin(String password, String address, String email, Instant birth, Gender gender,
			Set<Role> roles, String phone) {
		this.password = password;
		this.address = address;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.roles = roles;
		this.phone = phone;
	}
	
	public void updateBasic(String password, String address, String email, Instant birth, Gender gender, String phone) {
		this.password = password;
		this.address = address;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
	}
	
	public void updateSelfInfo(String name, Image imageThumb) {
		this.name = name;
		this.imageThumb = imageThumb;
	}
	
	public void updateCover(Image cover) {
		this.imageCover = cover;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}