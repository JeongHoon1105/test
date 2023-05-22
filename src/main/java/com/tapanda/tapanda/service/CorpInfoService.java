package com.tapanda.tapanda.service;

import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.model.CorporationInfo;
import com.tapanda.tapanda.requestDto.CorpInfoRequestDto;

public interface CorpInfoService {
	
	Optional<CorporationInfo> findByUserUid(String uid);

	Optional<CorporationInfo> registerCorpInfo(@Valid CorpInfoRequestDto request, String userUid);

	Optional<CorporationInfo> updateCorpInfo(@Valid CorpInfoRequestDto request, String userUid);

	public void deleteByUid(String uid);
}