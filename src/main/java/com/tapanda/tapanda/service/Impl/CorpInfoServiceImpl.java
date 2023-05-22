package com.tapanda.tapanda.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.enum_.Sectors;
import com.tapanda.tapanda.exception.CorpInfoNotFoundException;
import com.tapanda.tapanda.exception.DateParseException;
import com.tapanda.tapanda.exception.RoleNotFoundException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.CorporationInfo;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.repository.CorpInfoRepository;
import com.tapanda.tapanda.requestDto.CorpInfoRequestDto;
import com.tapanda.tapanda.service.CorpInfoService;
import com.tapanda.tapanda.service.RoleService;
import com.tapanda.tapanda.service.SellerService;
import com.tapanda.tapanda.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CorpInfoServiceImpl implements CorpInfoService {

	private final CorpInfoRepository corpInfoRepo;
	private final UserService userSevice;
	private final SellerService sellerService;
	private final RoleService roleService;

	@Override
	public Optional<CorporationInfo> findByUserUid(String uid) {
		// TODO Auto-generated method stub
		User user = userSevice.findByUid(uid)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		return corpInfoRepo.findByUserId(user.getId());
	}

	@Override
	public Optional<CorporationInfo> registerCorpInfo(@Valid CorpInfoRequestDto request, String userUid) {
		// TODO Auto-generated method stub
		User user = userSevice.findByUid(userUid)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		Seller seller = sellerService.findByUserId(user.getId())
				.orElseThrow(() -> new UserNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		try {
			Instant anniversary = new SimpleDateFormat("yyyy-MM-dd").parse(request.getAnniversary()).toInstant();
			Instant birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthDate()).toInstant();
			Instant rBirth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getRepresentBirthDate()).toInstant();
			CorporationInfo corpInfo = corpInfoRepo.save(request.toEntity(seller, anniversary, birth, rBirth));
			return Optional.ofNullable(corpInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getAnniversary(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public Optional<CorporationInfo> updateCorpInfo(@Valid CorpInfoRequestDto request, String userUid) {
		// TODO Auto-generated method stub
		User user = userSevice.findByUid(userUid)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		CorporationInfo corpInfo = corpInfoRepo.findByUserId(user.getId())
				.orElseThrow(() -> new CorpInfoNotFoundException("해당 판매자 법인 찾을수 없습니다.", null, null));
		try {
			Instant anniversary = new SimpleDateFormat("yyyy-MM-dd").parse(request.getAnniversary()).toInstant();
			Instant birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthDate()).toInstant();
			Instant rBirth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getRepresentBirthDate()).toInstant();
			corpInfo.update(request.getCompanyName(), request.getCorporateName(), request.getCorporateNumber(),
					request.getRepresentativeName(), rBirth, request.getLocation(), request.getZipCode(),
					request.getRepresentPhone(), anniversary, request.getCapital(), request.getEmployeesNumber(),
					Sectors.valueOf(request.getSector()), request.getWorkDetail(), request.getCompanyUrl(),
					request.getUserName(), request.getAddress(), birth, Gender.valueOf(request.getGender()),
					request.getAnnualSales());
			return Optional.ofNullable(corpInfoRepo.save(corpInfo));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getAnniversary(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public void deleteByUid(String uid) {
		// TODO Auto-generated method stub
		User user = userSevice.findByUid(uid)
				.orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		CorporationInfo corpInfo = corpInfoRepo.findByUserId(user.getId())
				.orElseThrow(() -> new CorpInfoNotFoundException("해당 판매자 법인 찾을수 없습니다.", null, null));
		corpInfoRepo.delete(corpInfo);;
	}

}