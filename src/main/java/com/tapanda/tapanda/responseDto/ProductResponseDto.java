package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductResponseDto {

    private Long productId;
    private String consulType;
    private String title;
    private String subtitle;
    private String serviceDescription;
    private Integer servicePrice;
    private Integer deliveryTime;
    private Integer maxBuy;
    private String purchaseRequest;
    private Boolean estimateAccept;
    private Boolean estimateRequired;
    private String estimateRequest;
    private Boolean deliveryReceipt;
    private Boolean rating;
    private Boolean chatting;
    private Boolean productConfirm;
    private Long category_id;
    private Long seller_id;

    public ProductResponseDto(Product product) {

        this.productId = product.getId();
        this.consulType = product.getConsulType().toString();
        this.title = product.getTitle();
        this.subtitle = product.getSubtitle();
        this.serviceDescription = product.getServiceDescription();
        this.servicePrice = product.getServicePrice();
        this.deliveryTime = product.getDeliveryTime();
        this.maxBuy = product.getMaxBuy();
        this.purchaseRequest = product.getPurchaseRequest();
        this.estimateAccept = product.getEstimateAccept();
        this.estimateRequired = product.getEstimateRequired();
        this.estimateRequest = product.getEstimateRequest();
        this.deliveryReceipt = product.getDeliveryReceipt();
        this.rating = product.getRating();
        this.chatting = product.getChatting();
        this.productConfirm = product.getProductConfirm();
        this.category_id = product.getCategory().getId();
        this.seller_id = product.getSeller().getId();
    }
}