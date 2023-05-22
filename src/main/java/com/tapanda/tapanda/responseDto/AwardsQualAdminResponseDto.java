package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Awards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AwardsQualAdminResponseDto {

    private Long awardsQualId;

    private String awardsCategory;

    private String title;

    private String date;

    private Long sellerId;

    public AwardsQualAdminResponseDto(Awards awards) {
        this.awardsQualId = awards.getAwardsQualId();
        this.awardsCategory = awards.getAwardsCategory().toString();
        if (awards.getDate() != null) {
            this.date = awards.getDate().toString();
        }
        this.title = awards.getTitle();
        this.sellerId = awards.getSeller().getId();
    }


}
