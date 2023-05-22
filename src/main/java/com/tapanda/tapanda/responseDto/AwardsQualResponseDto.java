package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Awards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AwardsQualResponseDto {

    private String awardsCategory;

    private String title;

    private String date;

    public AwardsQualResponseDto(Awards awards) {
        this.awardsCategory = awards.getAwardsCategory().toString();
        if (awards.getDate() != null) {
            this.date = awards.getDate().toString();
        }
        this.title = awards.getTitle();
    }

}
