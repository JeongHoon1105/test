package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioResponseDto {

    private String title;

    private String detail;

    private String upDate;

    private String youtubeLink;

    private String imageUrl;

    public PortfolioResponseDto(Portfolio port) {
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
