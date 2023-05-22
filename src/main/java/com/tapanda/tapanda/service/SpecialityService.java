package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.model.Speciality;
import com.tapanda.tapanda.requestDto.SpecialityRequestDto;

public interface SpecialityService {

    List<Speciality> findAllByUserId(Long userId);

    Optional<Speciality> findBySpecId(Long specId);

    Boolean existsBySpecId(Long specId);

	Optional<Speciality> registerSpeciality(@Valid SpecialityRequestDto request, Long userId);
	
    void deleteById(Long specId);

    Optional<Speciality> updateSpeciality(@Valid SpecialityRequestDto request, Long specId);

    void deleteBySellerId(Long sellerId);

}
