package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComQualResponseDto {

    private String name;

    private String position;

    private String startDate;

    private String finishDate;

    private String detail;

    public ComQualResponseDto(Company company) {
        this.name = company.getName();
        this.position = company.getPosition();
        if (company.getStartDate() != null) {
            this.startDate = company.getStartDate().toString();
        }
        if (company.getFinishDate() != null) {
            this.finishDate = company.getFinishDate().toString();
        }
        this.detail = company.getDetail();
    }
}
