package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.enum_.Consultation;
import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.model.Seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@ApiModel(value = "서비스 DTO")
@RequiredArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "서비스 방식을 선택해주세요.")
    @ApiModelProperty(value = "서비스 방식", required = true, allowableValues = "String")
    private String consulType;

    @NotBlank(message = "서비스 카테고리를 선택해주세요.")
    @ApiModelProperty(value = "서비스 카테고리", required = true, allowableValues = "String")
    private String categoryCode;

    @NotBlank(message = "서비스 제목을 입력해주세요.")
    @ApiModelProperty(value = "서비스 제목", required = true, allowableValues = "String")
    private String title;

    @NotBlank(message = "서비스 보충설명을 입력해주세요.")
    @ApiModelProperty(value = "서비스 보충설명", required = true, allowableValues = "String")
    private String subtitle;

    @NotBlank(message = "서비스 내용을 입력해주세요.")
    @ApiModelProperty(value = "서비스 내용", required = true, allowableValues = "String")
    private String serviceDescription;

    @NotNull(message = "서비스 가격을 입력해주세요.")
    @ApiModelProperty(value = "서비스 가격", required = true, allowableValues = "Integer")
    private Integer servicePrice;

    @NotNull(message = "예상 배송 일수를 입력해주세요.")
    @ApiModelProperty(value = "예상 배송 일수", required = true, allowableValues = "Integer")
    private Integer deliveryTime;

    @NotNull(message = "주문 가능 건수을 입력해주세요.")
    @ApiModelProperty(value = "주문 가능 건수", required = true, allowableValues = "Integer")
    private Integer maxBuy;

    @NotBlank(message = "구입시 유의사항을 입력해주세요.")
    @ApiModelProperty(value = "구입시 유의사항", required = true, allowableValues = "String")
    private String purchaseRequest;

    @ApiModelProperty(value = "견적 상담 접수")
    private Boolean estimateAccept;

    @ApiModelProperty(value = "견적 상담 필수 설정")
    private Boolean estimateRequired;

    @ApiModelProperty(value = "견적 상담 유의사항")
    private String estimateRequest;

    @ApiModelProperty(value = "물품 배송 가능여부")
    private Boolean deliveryReceipt;

    @ApiModelProperty(value = "평가 표시 가능여부")
    private Boolean rating;

    @ApiModelProperty(value = "화상 채팅 가능여부")
    private Boolean chatting;

    @ApiModelProperty(value = "서비스 공개/비공개")
    private Boolean productConfirm;

    public Product toEntity(Seller seller, Category category) {
        return Product.builder()
                .consulType(Consultation.valueOf(consulType))
                .title(title)
                .subtitle(subtitle)
                .serviceDescription(serviceDescription)
                .servicePrice(servicePrice)
                .deliveryTime(deliveryTime)
                .maxBuy(maxBuy)
                .purchaseRequest(purchaseRequest)
                .estimateAccept(estimateAccept)
                .estimateRequired(estimateRequired)
                .estimateRequest(estimateRequest)
                .deliveryReceipt(deliveryReceipt)
                .rating(rating)
                .chatting(chatting)
                .productConfirm(productConfirm)
                .category(category)
                .seller(seller)
                .build();
    }
}