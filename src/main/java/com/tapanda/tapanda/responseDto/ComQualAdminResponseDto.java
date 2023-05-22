package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComQualAdminResponseDto {

    private Long comQualId;

    private String name;

    private String position;

    private String startDate;

    private String finishDate;

    private String detail;

    private Long sellerId;

    public ComQualAdminResponseDto(Company company) {
        this.comQualId = company.getComQualId();
        this.name = company.getName();
        this.position = company.getPosition();
        if (company.getStartDate() != null) {
            this.startDate = company.getStartDate().toString();
        }
        if (company.getFinishDate() != null) {
            this.finishDate = company.getFinishDate().toString();
        }
        this.detail = company.getDetail();
        this.sellerId = company.getSeller().getId();
    }


}
