package com.tapanda.tapanda.model;

import com.tapanda.tapanda.enum_.Consultation;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Entity(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    private Long id;

    @Column(name = "CONSULTATION_TYPE")
    @Enumerated(EnumType.STRING)
    private Consultation consulType;

    @Column(name = "SERVICE_TITLE")
    @NotNull
    private String title;

    @Column(name = "SERVICE_SUBTITLE")
    @NotNull
    private String subtitle;

    @Column(name = "SERVICE_DESCRIPTION")
    @NotNull
    private String serviceDescription;

    @Column(name = "SERVICE_PRICE")
    @NotNull
    private Integer servicePrice;

    @Column(name = "DELIVERY_time")
    @NotNull
    private Integer deliveryTime;

    @Column(name = "MAX_CAN_BUY")
    @NotNull
    private Integer maxBuy;

    @Column(name = "PURCHASE_REQUEST")
    private String purchaseRequest;

    @Column(name = "ESTIMATE_ACCEPT", columnDefinition = "TINYINT")
    private Boolean estimateAccept;

    @Column(name = "ESTIMATE_REQUIRED", columnDefinition = "TINYINT")
    private Boolean estimateRequired;

    @Column(name = "ESTIMATE_REQUEST")
    private String estimateRequest;

    @Column(name = "DELIVERY_RECEIPT", columnDefinition = "TINYINT")
    private Boolean deliveryReceipt;

    @Column(name = "RATING", columnDefinition = "TINYINT")
    private Boolean rating;

    @Column(name = "CHATTING", columnDefinition = "TINYINT")
    private Boolean chatting;

    @Column(name = "PRODUCT_CONFIRM")
    private Boolean productConfirm;

    @OneToOne
    @JoinColumn(name = "SERVICE_CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaidOption> paidOption;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Faq> faq;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Video> video;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @Builder
    public Product(Consultation consulType, String title, String subtitle, String serviceDescription,
                   Integer servicePrice, Integer deliveryTime, Integer maxBuy, String purchaseRequest,
                   Boolean estimateAccept, Boolean estimateRequired, String estimateRequest, Boolean deliveryReceipt,
                   Boolean rating, Boolean chatting, Boolean productConfirm, Category category, Seller seller) {

        super();
        this.consulType = consulType;
        this.title = title;
        this.subtitle = subtitle;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.deliveryTime = deliveryTime;
        this.maxBuy = maxBuy;
        this.purchaseRequest = purchaseRequest;
        this.estimateAccept = estimateAccept;
        this.estimateRequired = estimateRequired;
        this.estimateRequest = estimateRequest;
        this.deliveryReceipt = deliveryReceipt;
        this.rating = rating;
        this.productConfirm = productConfirm;
        this.chatting = chatting;
        this.category = category;
        this.seller = seller;
    }

    public void update(Consultation consulType, String title, String subtitle, String serviceDescription,
                       Integer servicePrice, Integer deliveryTime, Integer maxBuy, String purchaseRequest,
                       Boolean estimateAccept, Boolean estimateRequired, String estimateRequest, Boolean deliveryReceipt,
                       Boolean rating, Boolean chatting, Boolean productConfirm, Category category) {

        this.consulType = consulType;
        this.title = title;
        this.subtitle = subtitle;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.deliveryTime = deliveryTime;
        this.maxBuy = maxBuy;
        this.purchaseRequest = purchaseRequest;
        this.estimateAccept = estimateAccept;
        this.estimateRequired = estimateRequired;
        this.estimateRequest = estimateRequest;
        this.deliveryReceipt = deliveryReceipt;
        this.rating = rating;
        this.productConfirm = productConfirm;
        this.chatting = chatting;
        this.category = category;
    }
}