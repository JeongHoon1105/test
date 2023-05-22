package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerAdminResponseDto {

    private Long sellerId;

    private Long userId;

    private String selfIntro;

    private String cPhrase;

    private String schedule;

    private String jobStatus;

    public SellerAdminResponseDto(Seller seller) {
        this.sellerId = seller.getId();
        this.userId = seller.getUser().getId();
        this.jobStatus = seller.getJobStatus().toString();
        this.selfIntro = seller.getSelfIntro();
        this.cPhrase = seller.getCPhrase();
        this.schedule = seller.getSchedule();
    }

}
