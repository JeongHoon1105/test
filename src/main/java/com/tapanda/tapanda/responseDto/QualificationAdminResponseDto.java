package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QualificationAdminResponseDto {

    private Long qualId;

    private String name;

    private String date;

    private Long sellerId;

    public QualificationAdminResponseDto (Qualification qual) {
        this.qualId = qual.getQualId();
        this.name = qual.getName();
        if (qual.getDate() != null) {
            this.date = qual.getDate().toString();
        }
        this.sellerId = qual.getSeller().getId();
    }

}
