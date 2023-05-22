package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QualificationResponseDto {

    private String name;

    private String date;

    public QualificationResponseDto (Qualification qual) {
        this.name = qual.getName();
        if (qual.getDate() != null) {
            this.date = qual.getDate().toString();
        }
    }
}
