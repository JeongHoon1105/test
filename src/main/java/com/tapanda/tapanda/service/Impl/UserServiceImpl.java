package com.tapanda.tapanda.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.enum_.JobStatus;
import com.tapanda.tapanda.enum_.RoleName;
import com.tapanda.tapanda.exception.DateParseException;
import com.tapanda.tapanda.exception.ImageDuplicateException;
import com.tapanda.tapanda.exception.ImageNotFoundException;
import com.tapanda.tapanda.exception.RoleNotFoundException;
import com.tapanda.tapanda.exception.UserDuplicateException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Image;
import com.tapanda.tapanda.model.Role;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.repository.UserRepository;
import com.tapanda.tapanda.requestDto.UserAdminRequestDto;
import com.tapanda.tapanda.requestDto.UserRequestDto;
import com.tapanda.tapanda.requestDto.UserSelfInfoRequestDto;
import com.tapanda.tapanda.requestDto.UserUpdateAdminRequestDto;
import com.tapanda.tapanda.service.FileStorageService;
import com.tapanda.tapanda.service.ImageService;
import com.tapanda.tapanda.service.RoleService;
import com.tapanda.tapanda.service.SellerService;
import com.tapanda.tapanda.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final SellerService sellerService;
	private final ImageService imageService;
	private final FileStorageService fileStorageService;

	@Override
	public Optional<User> findByUid(String uid) {
		// TODO Auto-generated method stub
		if (!existsByUid(uid)) {
			throw new UserNotFoundException("사용자를 찾을수 없습니다.", null, null);
		}
		return userRepository.findByUid(UUID.fromString(uid));
	}

	@Override
	public Boolean existsByUid(String uid) {
		// TODO Auto-generated method stub
		return userRepository.existsByUid(UUID.fromString(uid));
	}

	@Override
	@Transient
	public Optional<User> registerUser(@Valid UserRequestDto request) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (existsByEmail(request.getEmail())) {
			throw new UserDuplicateException("duplicate user", null, null);
		}
		Set<Role> roles = request.getRoles().stream().map(name -> {
			final Role role = roleService.findByName(name)
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
			return role;
		}).collect(Collectors.toSet());
		if (roles.isEmpty()) {
			roles.add(roleService.findByName(RoleName.ROLE_USER.toString())
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null)));
		}
		try {
			Instant birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()).toInstant();
			request.setPassword(encoder.encode(request.getPassword()));
			User user = userRepository.save(request.toEntity(roles, birth));
			Role sellerRole = roleService.findByName(RoleName.ROLE_SELLER.toString())
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
			if (roles.contains(sellerRole)) {
				Seller seller = new Seller();
				seller.setUser(user);
				sellerService.save(seller);
			}
			return Optional.ofNullable(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getBirth(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	private Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	private Boolean existsByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.existsByName(name);
	}

	@Override
	public UserDetails findUserByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(name)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", name, null));
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<User> updateUserBasicInfoByAdmin(@Valid UserUpdateAdminRequestDto request, Long id) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = findById(id).orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		try {
			Instant birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()).toInstant();
			Set<Role> roles = request.getRoles().stream().map(name -> {
				final Role role = roleService.findByName(name)
						.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
				return role;
			}).collect(Collectors.toSet());
			if (roles.isEmpty()) {
				roles.add(roleService.findByName(RoleName.ROLE_USER.toString())
						.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null)));
			}
			String password = request.getPassword() == null ? encoder.encode(user.getPassword())
					: request.getPassword();
			user.updateBasicAdmin(password, request.getAddress(), request.getEmail(), birth,
					Gender.valueOf(request.getGender()), roles, request.getPhone());
			return Optional.ofNullable(userRepository.save(user));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getBirth(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public Optional<User> registerUserByAdmin(@Valid UserAdminRequestDto request) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (existsByEmail(request.getEmail())) {
			throw new UserDuplicateException("duplicate user", null, null);
		}
		Set<Role> roles = request.getRoles().stream().map(name -> {
			final Role role = roleService.findByName(name)
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
			return role;
		}).collect(Collectors.toSet());
		if (roles.isEmpty()) {
			roles.add(roleService.findByName(RoleName.ROLE_USER.toString())
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null)));
		}
		try {
			Instant birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()).toInstant();
			request.setPassword(encoder.encode(request.getPassword()));
			User user = userRepository.save(request.toEntity(roles, birth));
			Role sellerRole = roleService.findByName(RoleName.ROLE_SELLER.toString())
					.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
			if (roles.contains(sellerRole)) {
				Seller seller = new Seller();
				seller.setUser(user);
				sellerService.save(seller);
			}
			return Optional.ofNullable(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getBirth(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public List<User> findAllByRoleName(String name) {
		// TODO Auto-generated method stub
		Role role = roleService.findByName(name)
				.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null));
		return userRepository.findAllByRoles(role);
	}

	@Override
	public List<User> findAllBySearchWord(String word) {
		// TODO Auto-generated method stub
		return userRepository.findByNameContainingOrEmailContaining(word, word);
	}

	@Override
	public void deleteByUid(String uid) {
		// TODO Auto-generated method stub
		User user = findByUid(uid).orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		if (sellerService.existsByUserId(user.getId())) {
			sellerService.deleteById(user.getId());
		}
		userRepository.delete(user);
		imageService.deleteById(user.getImageThumb().getImageId());
		imageService.deleteById(user.getImageCover().getImageId());
	}

	@Override
	public Boolean checkNameDuplication(String name) {
		return existsByName(name);
	}

	@Override
	public Boolean checkEmailDuplication(String email) {
		// TODO Auto-generated method stub
		return existsByEmail(email);
	}

	@Override
	public Optional<User> updateUserSelfInfo(@Valid UserSelfInfoRequestDto request, Long id, MultipartFile file) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		if (user.getAuthorities().contains(roleService.findByName(RoleName.ROLE_SELLER.toString())
				.orElseThrow(() -> new RoleNotFoundException("역할를 찾을수 없습니다.", null, null)))) {
			Seller seller = sellerService.findByUserId(user.getId())
					.orElseThrow(() -> new UserNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
			seller.updateSelfInfo(request.getSelfIntro(), request.getCPhrase(), request.getShedule(),
					JobStatus.valueOf(request.getJobStatus()));
			sellerService.save(seller);
		}
		Image fileUpload = null;
		if (file != null) {
			fileUpload = imageService.save(fileStorageService.userFileUpload(file).toEntity())
					.orElseThrow(() -> new ImageDuplicateException("사진을 복제합니다", null, null));
		}
		if (user.getImageThumb() != null) {
			Image beforeImage = imageService.findByIid(user.getImageThumb().getIid().toString())
					.orElseThrow(() -> new ImageNotFoundException("사진을 찾을수 없습니다.", null, null));
			if (file != null) {
				fileStorageService.userFileDelete(beforeImage);
				imageService.deleteById(beforeImage.getImageId());
			} else {
				fileUpload = beforeImage;
			}
		}
		user.updateSelfInfo(request.getUsername(), fileUpload);
		User userUp = userRepository.save(user);
		return Optional.ofNullable(userUp);
	}

	@Override
	public Optional<User> updateUserCover(User user, MultipartFile file) {
		// TODO Auto-generated method stub
		Image fileUpload = null;
		Image beforeImage = null;
		if (file != null) {
			fileUpload = imageService.save(fileStorageService.userFileUpload(file).toEntity())
					.orElseThrow(() -> new ImageDuplicateException("사진을 복제합니다", null, null));
		}
		if (user.getImageCover() != null) {
			beforeImage = imageService.findByIid(user.getImageCover().getIid().toString())
					.orElseThrow(() -> new ImageNotFoundException("사진을 찾을수 없습니다.", null, null));
		}
		user.updateCover(fileUpload);
		User userUp = userRepository.save(user);

		if (beforeImage != null) {
			fileStorageService.userFileDelete(beforeImage);
			imageService.deleteById(beforeImage.getImageId());
		}
		return Optional.ofNullable(userUp);
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("사용자를 찾을수 없습니다.", null, null);
		}
		return userRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		userRepository.delete(user);
	}

	@Override
	public Boolean isSeller(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.isSeller(userId);
	}
}