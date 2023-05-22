package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioAdminResponseDto {
	private Long portId;

	private String categoryCode;

	private String title;

	private String detail;

	private String upDate;

	private String youtubeLink;

	private String imageUrl;

	public PortfolioAdminResponseDto(Portfolio port) {
		this.portId = port.getPortId();
		this.categoryCode = port.getCategory().getCode();
		this.title = port.getTitle();
		this.detail = port.getDetail();
		if (port.getUpDate() != null) {
			this.upDate = port.getUpDate().toString();
		}
		if (port.getYoutubeLink() != null) {
			this.youtubeLink = port.getYoutubeLink().toString();
		}
		if (port.getImage() != null) {
			this.imageUrl = port.getImage().getUrl();
		}
	}

}
