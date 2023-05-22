package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerResponseDto {

    private String selfIntro;

    private String cPhrase;

    private String schedule;

    private String jobStatus;

    public SellerResponseDto(Seller seller) {
        this.jobStatus = seller.getJobStatus().toString();
        this.selfIntro = seller.getSelfIntro();
        this.cPhrase = seller.getCPhrase();
        this.schedule = seller.getSchedule();
    }
}
