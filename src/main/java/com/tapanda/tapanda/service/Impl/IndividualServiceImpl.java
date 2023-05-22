package com.tapanda.tapanda.service.Impl;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.exception.*;
import com.tapanda.tapanda.model.Individual;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.repository.IndividualRepository;
import com.tapanda.tapanda.requestDto.IndividualAdminRequestDto;
import com.tapanda.tapanda.service.IndividualService;
import com.tapanda.tapanda.service.SellerService;
import com.tapanda.tapanda.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualServiceImpl implements IndividualService {

    private final IndividualRepository individualRepository;

    private final UserService userService;
    private final SellerService sellerService;

    @Override
    public Optional<Individual> registerIndividualByAdmin(@Valid IndividualAdminRequestDto request, String uid) {
		User user = userService.findByUid(uid)
				.orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다.", null, null));
        Seller seller = sellerService.findByUserId(user.getId())
                .orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을 수 없습니다.", null, null));
        if (!existsBySellerId(seller.getId())) {
            try {
                Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()).toInstant();
                Individual individualup = individualRepository.save(request.toEntity(seller, date));
                return Optional.ofNullable(individualup);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                throw new DateParseException(request.getBirth(), "날짜형식이 옳바르지 않습니다.");
            }
        } else {
            throw new IndividualRegisterException("이미 출품정보가 존재합니다.", null, null);
        }
    }

    @Override
    public Boolean existsBySellerId(Long sellerId) {
        return individualRepository.existsBySellerId(sellerId);
    }

    @Override
    public Optional<Individual> findByUid(String uid) {
        return individualRepository.findByUid(uid);
    }

    @Override
    public Optional<Individual> updateIndividualByAdmin(IndividualAdminRequestDto request, String uid) {
        Individual individual = findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자를 찾을 수 없습니다.", null, null));
		try {
			Instant birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()).toInstant();
			individual.update(request.getName(), request.getAddress(),
					birth, Gender.valueOf(request.getGender()), request.getClassitication());
			return Optional.ofNullable(individualRepository.save(individual));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getBirth(), "날짜형식이 옳바르지 않습니다.");
		}
    }

    @Override
    public void deleteByUid(String uid) {
        Individual individual = findByUid(uid)
                .orElseThrow(() -> new IndividualNotFoundException("해당 출품정보를 찾을 수 없습니다.", null, null));
        individualRepository.delete(individual);
    }


}
