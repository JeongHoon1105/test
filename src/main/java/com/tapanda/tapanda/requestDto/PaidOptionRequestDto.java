package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.model.PaidOption;
import com.tapanda.tapanda.model.Product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@ApiModel(value = "유료옵션 DTO")
@RequiredArgsConstructor
public class PaidOptionRequestDto {

    @ApiModelProperty(value = "유료옵션 내용")
    private String detail;

    @ApiModelProperty(value = "유료옵션 가격")
    private Integer price;

    @ApiModelProperty(value = "유료옵션 공개/비공개")
    private Boolean status;

    public PaidOption toEntity(Product product) {

        return PaidOption.builder()
                .detail(detail)
                .price(price)
                .status(status)
                .product(product)
                .build();
    }
}