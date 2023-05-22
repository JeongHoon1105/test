package com.tapanda.tapanda.requestDto;

import java.time.Instant;

import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.model.Image;
import com.tapanda.tapanda.model.Portfolio;
import com.tapanda.tapanda.model.Seller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "포트폴리오 DTO")
@AllArgsConstructor
public class PortfolioRequestDto {

	private String categoryCode;

	private String title;

	private String detail;

	private String upDate;

	private String youtubeLink;

	public Portfolio toEntity(Seller seller, Image image, Category category, Instant upDate) {
		// TODO Auto-generated method stub
		return Portfolio.builder().detail(detail).category(category).image(image).seller(seller)
				.youtubeLink(youtubeLink).upDate(upDate).title(title).build();
	}

}
