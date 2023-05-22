package com.tapanda.tapanda.requestDto;

import java.time.Instant;

import com.tapanda.tapanda.model.Company;
import com.tapanda.tapanda.model.Seller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "경력 DTO")
@AllArgsConstructor
public class CompanyQualRequestDto {

	private String name;

	private String position;

	private String startDate;

	private String finishDate;

	private String detail;

	public Company toEntity(Seller seller, Instant startDate, Instant endDate) {
		// TODO Auto-generated method stub
		return Company.builder().detail(detail).finishDate(endDate).name(name).seller(seller).position(position)
				.startDate(startDate).build();
	}
}
