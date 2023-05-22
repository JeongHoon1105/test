package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.PaidOption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaidOptionResponseDto {

    private String detail;

    private Integer price;

    private Boolean optionStatus;

    public PaidOptionResponseDto(PaidOption paidOption) {
        this.detail = paidOption.getDetail();
        this.price = paidOption.getPrice();
        this.optionStatus = paidOption.getStatus();
    }
}
